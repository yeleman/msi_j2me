
package msisr;
import javax.microedition.lcdui.*;
import msisr.MSIMIDlet.*;
import msisr.Configuration.*;
import msisr.ProvidedServicesReport.*;
import msisr.FinancialReport.*;
import msisr.StocksReport.*;

/**
 * J2ME Form allowing choice between new report or continuation
 * if continue, stores choice in config.
 * @author rgaudin
 */
public class UpdateOrNewForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Quitter", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Valider", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);

    private MSIMIDlet midlet;

    private StringItem text;
    private ChoiceGroup selection;

    private Configuration config;

    public UpdateOrNewForm(MSIMIDlet midlet) {
        super("Que voulez-vous faire ?");
        this.midlet = midlet;

        config = new Configuration();

        text = new StringItem(null, "Souhaitez vous reprendre le dernier rapport ou créer un nouveau?");
        String[] choices = {"Reprendre précédent", "Créer nouveau"};
        selection = new ChoiceGroup("Je souhaite:", ChoiceGroup.EXCLUSIVE, choices, null);

        append(text);
        append(selection);

        addCommand(CMD_SAVE);
        addCommand(CMD_EXIT);
        addCommand(CMD_HELP);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        // Help command displays Help Form
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "update_or_new");
            this.midlet.display.setCurrent(h);
        }

        // exit command goes back to main menu.
        if (c == CMD_EXIT) {
            this.midlet.destroyApp(false);
            this.midlet.notifyDestroyed();
        }

        // save command stores choice in DB (if continue) or errors then
        // goes to main menu
        if (c == CMD_SAVE) {
            // continue existing does nothing.
            if (selection.getSelectedIndex() == 0) {
                Alert alert = new Alert("Poursuite de rapport", "Les dernières données ont été chargées.", null, AlertType.INFO);
                alert.setTimeout(3000);
                this.midlet.display.setCurrent(alert, this.midlet.mainMenu);
            } else {
                config.set("has_data", "false");
                // remove all report databases
                ProvidedServicesReport u5 = new ProvidedServicesReport();
                u5.delete();

                FinancialReport o5 = new FinancialReport();
                o5.delete();

                StocksReport pw = new StocksReport();
                pw.delete();

                this.midlet.refreshMenu();
                this.midlet.display.setCurrent (this.midlet.mainMenu);
            }
        }
    }
}
