
package msi_sr;

import javax.microedition.lcdui.*;
import msi_sr.Configuration.*;
import msi_sr.Constants.*;

/**
 * J2ME Form displaying a long help text
 * Instanciated with a section paramater
 * which triggers appropriate text.
 * @author Fad
 */

public class HelpForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour",
                                                        Command.BACK, 1);

    private StringItem helpText;
    MsiMidlet midlet;
    Displayable returnTo;


    public HelpForm(MsiMidlet midlet, Displayable d, String section) {
        super("Aide");
        this.midlet = midlet;
        this.returnTo = d;

        this.getContentFromSection(section);

        append(helpText);
        addCommand(CMD_EXIT);
        this.setCommandListener (this);
      }


    private void getContentFromSection(String section) {
        String text;
        if (section.equalsIgnoreCase("mainmenu")) {
            text = "Ouvrez le formulaire qui correspond à votre opération puis " +
                   "renseignez les champs et envoyez.\n" +
                   "Un SMS non envoyé est sauvegardé dans <<Renvoi form>>.";

        } else if (section.equalsIgnoreCase("version")) {
            text = "msi_sr - Version " + Constants.version;

        } else if (section.equalsIgnoreCase("option")) {
            text = "Changez le numéro du serveur uniquement sur " +
                   "demande expresse de " + Constants.HEAD_OF_MAINTENANCE +
                   "\nUn mauvais numéro vous empêchera de transmettre vos" +
                   " rapports.\n";

        } else if (section.equalsIgnoreCase("saved_reports")) {
            text = "Vous pouvez envoyer tous les SMS à la fois ou le faire un à un.";

        } else if (section.equalsIgnoreCase("passwd")) {
            text = "Renseignez votre ancien mot de passe dans les champs adéquat.\n" +
                   "Ensuite, indiquez le nouveau mot de passe désiré. Celui-ci doit faire au moins 3 caractères.\n" +
                   "Vous recevrez un SMS du serveur confirmant ou non le changement de mot de passe.";
        } else {
            text = "Aucune aide disponible pour cet élément.";

        }

        helpText = new StringItem(null, text +
                                  "\n\nEn cas de problème, contactez " +
                                  Constants.HEAD_OF_MAINTENANCE + ".");
    }

    public void commandAction(Command c, Displayable d) {
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.returnTo);
        }
    }

}
