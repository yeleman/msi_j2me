
package msisr;
import javax.microedition.lcdui.*;
import msisr.MSIMIDlet.*;
import msisr.Configuration.*;
import msisr.SMSSender.*;
import msisr.MSIReport.*;

/**
 * Request per-report meta data and fire SMS
 * @author rgaudin
 */
public class SendReportForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SEND = new Command ("Envoyer", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);

    private MSIMIDlet midlet;

    private static final String[] monthList= {" --- ", "Janvier (01)", "Février (02)", "Mars (03)", "Avril (04)", "Mai (05)", "Juin (06)", "Juillet (07)", "Aout (08)", "Septembre (09)", "Octobre (10)", "Novembre (11)", "Décembre (12)"};
    private static final String[] yearList = {" --- ", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};

    private StringItem intro;
    private ChoiceGroup monthField;
    private ChoiceGroup yearField;
    private TextField usernameField;
    private TextField passwordField;

    private Configuration config;

    public SendReportForm(MSIMIDlet midlet) {
        super("Envoi du rapport mensuel");
        this.midlet = midlet;

        config = new Configuration();

        intro = new StringItem(null, "Indiquez la période concernée et donnez vos identifiants pour envoyer le rapport.");
        monthField = new ChoiceGroup("Mois:", ChoiceGroup.POPUP, monthList, null);
        yearField = new ChoiceGroup("Année:", ChoiceGroup.POPUP, yearList, null);
        usernameField = new TextField("Identifiant", config.get("username"), Constants.username_max_length, TextField.NON_PREDICTIVE);
        passwordField = new TextField("Mot de passe", null, Constants.password_max_length, TextField.SENSITIVE);

        this.append(intro);
        this.append(monthField);
        this.append(yearField);
        this.append(usernameField);
        this.append(passwordField);

        addCommand(CMD_SEND);
        addCommand(CMD_EXIT);
        addCommand(CMD_HELP);
        this.setCommandListener(this);
    }


    public boolean passwordIsValid() {
        if (passwordField.getString().indexOf(" ") == -1) {
            return true;
        }
        return false;
    }

    /*
     *
     * @return <code>true</code> if fields are properly field for sending
     * <code>false</code> otherwise.
     */
    public boolean canSubmit() {
        if (usernameField.getString().length() >= Constants.username_min_length &&
            passwordField.getString().length() >= Constants.password_min_length) {
            return true;
        }
        return false;
    }

    public void commandAction(Command c, Displayable d) {
        // Help command displays Help Form
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "send_report");
            this.midlet.display.setCurrent(h);
        }

        // exit command goes back to main Menu.
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.midlet.mainMenu);
        }

        // send command sends the SMS or displays errors.
        if (c == CMD_SEND) {

            Alert alert;


            // check password
            if (!this.passwordIsValid()) {
                alert = new Alert("Mot de passe incorrect", "L'espace n'est " +
                        "pas autorisé dans le mot de passe.", null,
                        AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // check username and password
            if (!this.canSubmit()) {
                alert = new Alert("Informations manquantes",
                        "Votre identifiant ou votre mot de passe est " +
                        "trop court.", null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // create the report
            MSIReport report = new MSIReport();
            report.username = usernameField.getString().replace(' ', '_');
            report.password = passwordField.getString().replace(' ', '_');
            report.month = monthField.getSelectedIndex();
            int year = -1;
            try {
                year = Integer.parseInt(yearField.getString(yearField.getSelectedIndex()));
            } catch (java.lang.NumberFormatException e) {
                //int year = -1;
            }
            report.year = year;

            if (SharedChecks.dateIsNotFuture(monthField.getSelectedIndex(),
                                          year) != true) {
                alert = new Alert("Période incorrect", "[La période] est dans le futur.", null,
                        AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            if (SharedChecks.dateIsNotPast(monthField.getSelectedIndex(),
                                          year) != true) {
                alert = new Alert("Période incorrect", "[La période] est dans le passé.", null,
                        AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // check validity and exit if it fails
            if (!(report.dataIsValid())) {
                alert = new Alert ("Informations incorrectes.",
                        report.errorMessage(), null, AlertType.ERROR);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // save username in DB
            config.set("username", report.username);

            // sends the sms and reply feedback
            SMSSender sms = new SMSSender();
            String number = config.get("server_number");
            if (sms.send(number, report.toSMSFormat())) {
                alert = new Alert ("Demande envoyée !",
                        "Vous allez recevoir une confirmation du serveur.",
                        null, AlertType.CONFIRMATION);
                this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
            } else {
                alert = new Alert ("Échec d'envoi SMS",
                        "Impossible d'envoyer la demande par SMS.", null,
                        AlertType.WARNING);
                this.midlet.display.setCurrent (alert, this);
            }

        }
    }
}
