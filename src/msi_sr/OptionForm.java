
package msi_sr;

import javax.microedition.lcdui.*;
import msi_sr.Configuration.*;
import msi_sr.Constants.*;
import msi_sr.HelpForm.*;

/**
 * J2ME Form allowing Server number, user_name region and district.
 * @author Fad
 */

public class OptionForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Enreg.", Command.OK, 2);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 4);

    MsiMidlet midlet;
    //Displayable returnTo;
    private String ErrorMessage = "";

    private Configuration config;
    private TextField numberField;
    private TextField user_nameField;

public OptionForm(MsiMidlet midlet) {
    super("Paramètres de transmission");
    this.midlet = midlet;

    config = new Configuration();

    // retrieve phone number from config
    // if not present, use constant
    String phone_number = "";
    phone_number = config.get("server_number");
    if (phone_number.equals("")) {
        phone_number = Constants.server_number;
    }


    numberField = new TextField ("Numéro du serveur:", phone_number, 8, TextField.PHONENUMBER);
    user_nameField = new TextField("Votre Identifiant", config.get("user_name"), 20, TextField.ANY);

    append(numberField);
    append(user_nameField);

    addCommand(CMD_EXIT);
    addCommand(CMD_HELP);
    addCommand(CMD_SAVE);

    this.setCommandListener(this);
  }

    /*
     * Whether all required fields are filled
     * @return <code>true</code> is all fields are filled
     * <code>false</code> otherwise.
     */
    public boolean isComplete() {
        // all fields are required to be filled.
        if (numberField.getString().length() == 0) {
            return false;
        }
        if (user_nameField.getString().length() == 0) {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        if (numberField.getString().length() < 8) {
            ErrorMessage = "[Numéro du serveur] n'est pas un numéro valide";
            return false;
        }
        return true;
    }

    public void commandAction(Command c, Displayable d) {
        // Help command displays Help Form
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "option");
            this.midlet.display.setCurrent(h);
        }

        // exit command goes back to Main Menu
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.midlet.mainMenu);
            return;
        }


        // save command stores new number in config or display errors.
        if (c == CMD_SAVE) {

            Alert alert;
            // check whether all fields have been completed
            // if not, we alert and don't do anything else.
            if (!this.isComplete()) {
                alert = new Alert("Données manquantes", "Tous les champs doivent être remplis!", null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            if (!this.isValid()) {
                alert = new Alert("Données incorrectes!", this.ErrorMessage,
                                  null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            if (config.set("server_number", numberField.getString()) &&
                config.set("user_name", user_nameField.getString())) {

                alert = new Alert ("Confirmation!", "Votre modification a bien été enregistrée.", null, AlertType.CONFIRMATION);
                this.midlet.startApp();
                this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
            } else {
                alert = new Alert ("Échec", "Impossible d'enregistrer cette modification.", null, AlertType.WARNING);
                this.midlet.display.setCurrent (alert, this);
            }
        }
    }
}
