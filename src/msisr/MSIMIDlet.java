package msisr;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import msisr.ProvidedServicesReport.*;
import msisr.EditNumberForm.*;
import msisr.ChangePasswordForm.*;
import msisr.HelpForm.*;
import msisr.ProvidedServicesForm.*;
import msisr.FinancialForm.*;
import msisr.StocksForm.*;
import msisr.UpdateOrNewForm.*;
import msisr.SendReportForm.*;
import msisr.RequestHelpForm.*;

/*
 * J2ME Midlet allowing user to fill and submit Malaria reports
 * @author rgaudin
 */
public class MSIMIDlet extends MIDlet implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Quitter", Command.EXIT, 1);
    private static final Command CMD_VERSION = new Command ("Version", Command.SCREEN, 2);
    private static final Command CMD_PASSWD = new Command ("Mot de passe", Command.SCREEN, 3);
    private static final Command CMD_SRVNUM = new Command ("Numéro serveur", Command.SCREEN, 4);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 5);
    private static final Command CMD_HOTLINE = new Command ("Hotline", Command.HELP, 6);

    public Display display;
    public List mainMenu;
    private Configuration config;

    public MSIMIDlet() {
        display = Display.getDisplay(this);
    }

    public void startApp() {

        config = new Configuration();

        String[] mainMenu_items = {"S.P", "R.F", "R.S", "Envoyer"};
        mainMenu = new List("Rapport mensuel PF/MSI", Choice.IMPLICIT, mainMenu_items, null);

        // setup menu
        mainMenu.setCommandListener (this);
        mainMenu.addCommand (CMD_EXIT);
        mainMenu.addCommand (CMD_HELP);
        mainMenu.addCommand (CMD_HOTLINE);
        mainMenu.addCommand (CMD_PASSWD);
        mainMenu.addCommand (CMD_SRVNUM);
        mainMenu.addCommand (CMD_VERSION);

        if (config.get("has_data").equalsIgnoreCase("true")) {
            UpdateOrNewForm update_form = new UpdateOrNewForm(this);
            display.setCurrent(update_form);
        } else {
            display.setCurrent(mainMenu);
        }

        // changes label names to proper one + OK if applicable
        refreshMenu();
    }

    /*
     * Rewrites names of Main Menu item based on completion
     */
    public void refreshMenu() {
        // create a report object to access data
        MSIReport report = new MSIReport();

        // rewrite menu item names based on validation
        mainMenu.set(0, menuItemText("provided_services", report.provided_services.dataIsValid()), null);
        mainMenu.set(1, menuItemText("financial", report.financial.dataIsValid()), null);
        mainMenu.set(2, menuItemText("stocks", report.stocks.dataIsValid()), null);
    }

    /*
     * Provides a name for menu item fields with completion details
     * Adds [OK] in front of label names if <code>is_ok</code> is <code>true</code>
     * @param slug the slug of the section
     * @param is_ok whether or not to ass [OK] extra text
     * @return a string to be set as menu item label
     */
    private String menuItemText(String slug, boolean is_ok) {
        String name;
        String ok_part = "[OK] ";
        if (slug.equalsIgnoreCase("provided_services"))
            name = "Services Prestés";
        else if (slug.equalsIgnoreCase("financial"))
            name = "Financier";
        else if (slug.equalsIgnoreCase("stocks"))
            name = "Stocks";
        else
            name = "";

        if (is_ok)
            return ok_part + name;
        else
            return name;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {

        // if it originates from the MainMenu list
        if (s.equals (mainMenu)) {
            // and is a select command
            if (c == List.SELECT_COMMAND) {

                    switch (((List) s).getSelectedIndex ()) {

                    // provided_services
                    case 0:
                        ProvidedServicesForm ps_form = new ProvidedServicesForm(this);
                        display.setCurrent (ps_form);
                        break;

                    // financial
                    case 1:
                        FinancialForm fr_form = new FinancialForm(this);
                        display.setCurrent (fr_form);
                        break;

                    // stocks
                    case 2:
                        StocksForm sr_form = new StocksForm(this);
                        display.setCurrent (sr_form);
                        break;

                    // send form
                    case 3:
                        // check validity and exit if it fails
                        MSIReport report = new MSIReport();
                        if (!(report.dataIsValid(true))) {
                            Alert alert = new Alert ("Informations incorrectes.", report.errorMessage(), null, AlertType.ERROR);
                            alert.setTimeout(3000);
                            this.display.setCurrent (alert);
                            return;
                        } else {
                            SendReportForm send_form = new SendReportForm(this);
                            display.setCurrent(send_form);
                        }
                        break;
                    }
            }
        }

        // help command displays Help Form.
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this, this.mainMenu, "mainmenu");
            display.setCurrent(h);
        }

        // hotline command displays RequestHelp Form.
        if (c == CMD_HOTLINE) {
            RequestHelpForm hot = new RequestHelpForm(this);
            display.setCurrent(hot);
        }

        // version command displays Help Form for "version"
        if (c == CMD_VERSION) {
            HelpForm v = new HelpForm(this, this.mainMenu, "version");
            display.setCurrent(v);
        }

        // srvnum command displays Edit Number Form.
        if (c == CMD_SRVNUM) {
            EditNumberForm f = new EditNumberForm(this);
            display.setCurrent(f);
        }

        // passwd command displays Change Password Form.
        if (c == CMD_PASSWD) {
            ChangePasswordForm f = new ChangePasswordForm(this);
            display.setCurrent(f);
        }

        // exit commands exits application completely.
        if (c == CMD_EXIT) {
            destroyApp(false);
            notifyDestroyed();
        }
    }
}