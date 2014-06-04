/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msi_sr;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import msi_sr.SMSStore.*;
import msi_sr.StoredSMS.*;
import msi_sr.OptionForm.*;
import msi_sr.ServiceForm.*;
import msi_sr.Constants.*;
import msi_sr.Configuration.*;
import msi_sr.SendSavedReports.*;
import msi_sr.ChangePasswordForm.*;

/**
 * @author Fad
 */

public class MsiMidlet extends MIDlet implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Quitter", Command.EXIT, 1);
    private static final Command CMD_VERSION = new Command ("Version", Command.SCREEN, 2);
    private static final Command CMD_SRVNUM = new Command ("Configuration", Command.SCREEN, 4);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 5);
    private static final Command CMD_PASSWD = new Command ("Mot de passe", Command.SCREEN, 6);

    public Display display;
    public List mainMenu;
    private Configuration config;

    public MsiMidlet() {
        display = Display.getDisplay(this);
    }

    public void startApp() {

    config = new Configuration();
    SMSStore store = new SMSStore();

    String[] mainMenu_ = {"Rapport service", "Rapport financier", "Rapport stocks",
                          "Renvoi form. (" + store.count() + ")"};

    if(config.get("user_name").equals("")){
        OptionForm f = new OptionForm(this);
        display.setCurrent(f);
    } else{
        mainMenu = new List("Formulaires", Choice.IMPLICIT, mainMenu_, null);
        // setup menu
        mainMenu.setCommandListener (this);
        mainMenu.addCommand (CMD_EXIT);
        mainMenu.addCommand (CMD_HELP);
        mainMenu.addCommand (CMD_VERSION);
        mainMenu.addCommand (CMD_SRVNUM);
        mainMenu.addCommand (CMD_PASSWD);
        display.setCurrent(mainMenu);
    }
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
                case 0:
                    ServiceForm service_form = new ServiceForm(this);
                    display.setCurrent(service_form);
                    break;
                case 1:
                    // FinancialFrom financial_form = new FinancialFrom(this);
                    // display.setCurrent(financial_form);
                    break;
                case 2:
                    // StockFrom stock_from = new StockFrom(this);
                    // display.setCurrent(stock_from);
                    break;
                case 3:
                    SendSavedReports saved_reports = new SendSavedReports(this);
                    display.setCurrent(saved_reports);
                    break;
                }
            }
        }

        // help command displays Help Form.
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this, this.mainMenu, "mainmenu");
            display.setCurrent(h);
        }

        // version command displays Help Form for "version"
        if (c == CMD_VERSION) {
            HelpForm v = new HelpForm(this, this.mainMenu, "version");
            display.setCurrent(v);
        }

        // srvnum command displays Edit Number Form.
        if (c == CMD_SRVNUM) {
            OptionForm f = new OptionForm(this);
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
