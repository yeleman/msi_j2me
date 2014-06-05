package msi_sr;

import javax.microedition.lcdui.*;

import msi_sr.Configuration.*;
import msi_sr.Constants.*;
import msi_sr.SharedChecks.*;

/**
 * J2ME Form displaying a long help text
 * Instanciated with a section paramater
 * which triggers appropriate text.
 * @author Fad
 */


public class FinancialFrom extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SEND = new Command ("Envoi.", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);

    MsiMidlet midlet;
    Displayable returnTo;

    private static final String[] monthList= {" --- ", "Janvier (01)", "Février (02)", "Mars (03)", "Avril (04)", "Mai (05)", "Juin (06)", "Juillet (07)", "Aout (08)", "Septembre (09)", "Octobre (10)", "Novembre (11)", "Décembre (12)"};
    private static final String[] yearList = {" --- ", "2014", "2015", "2016", "2017"};

    private String ErrorMessage = "";

    private Configuration config;
    private SMSStore store;

    private ChoiceGroup monthField;
    private ChoiceGroup yearField;

    private TextField intrauterine_devices_qty;
    private TextField intrauterine_devices_price;
    private TextField intrauterine_devices_revenue;
    private TextField implants_qty;
    private TextField implants_price;
    private TextField implants_revenue;
    private TextField injections_qty;
    private TextField injections_price;
    private TextField injections_revenue;
    private TextField pills_qty;
    private TextField pills_price;
    private TextField pills_revenue;
    private TextField male_condoms_qty;
    private TextField male_condoms_price;
    private TextField male_condoms_revenue;
    private TextField female_condoms_qty;
    private TextField female_condoms_price;
    private TextField female_condoms_revenue;
    private TextField hiv_tests_qty;
    private TextField hiv_tests_price;
    private TextField hiv_tests_revenue;
    private TextField iud_removal_qty;
    private TextField iud_removal_price;
    private TextField iud_removal_revenue;
    private TextField implant_removal_qty;
    private TextField implant_removal_price;
    private TextField implant_removal_revenue;
    private TextField user_password;

    String sep = " ";

    // DIU
    // Implant
    // Injectable
    // Pilule
    // Préservatif
    // Masculin
    // Préservatif
    // Féminin
    // Test  VIH
    // Retrait DIU
    // Retrait implant


    public FinancialFrom(MsiMidlet midlet) {
        super("Financial");
        this.midlet = midlet;
        config = new Configuration();
        store = new SMSStore();
        // periode
        monthField = new ChoiceGroup("Mois:", ChoiceGroup.POPUP, monthList, null);
        yearField = new ChoiceGroup("Année:", ChoiceGroup.POPUP, yearList, null);
        user_password = new TextField("Mot de passe:", null, 20, TextField.ANY);

        intrauterine_devices_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        intrauterine_devices_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        intrauterine_devices_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implants_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implants_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implants_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        injections_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        injections_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        injections_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        pills_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        pills_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        pills_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        male_condoms_qty = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        male_condoms_price = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        male_condoms_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        female_condoms_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        female_condoms_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        female_condoms_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_tests_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_tests_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_tests_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        iud_removal_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        iud_removal_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        iud_removal_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implant_removal_qty = new TextField("Nombre", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implant_removal_price = new TextField("Prix Unitaire", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implant_removal_revenue = new TextField("Revenu Mensuel", null, Constants.MAX_SIZE, TextField.DECIMAL);

        append(monthField);
        append(yearField);
        append("DIU");
        append(intrauterine_devices_qty);
        append(intrauterine_devices_price);
        append(intrauterine_devices_revenue);
        append("Implant");
        append(implants_qty);
        append(implants_price);
        append(implants_revenue);
        append("Injectable");
        append(injections_qty);
        append(injections_price);
        append(injections_revenue);
        append("Pilule");
        append(pills_qty);
        append(pills_price);
        append(pills_revenue);
        append("Préservatif Masculin");
        append(male_condoms_qty);
        append(male_condoms_price);
        append(male_condoms_revenue);
        append("Préservatif Féminin");
        append(female_condoms_qty);
        append(female_condoms_price);
        append(female_condoms_revenue);
        append("Préservatif Féminin");
        append(hiv_tests_qty);
        append(hiv_tests_price);
        append(hiv_tests_revenue);
        append("Retrait DIU");
        append(iud_removal_qty);
        append(iud_removal_price);
        append(iud_removal_revenue);
        append("Retrait implant");
        append(implant_removal_qty);
        append(implant_removal_price);
        append(implant_removal_revenue);
        append(user_password);

        addCommand(CMD_EXIT);
        addCommand(CMD_SEND);
        addCommand(CMD_HELP);
        this.setCommandListener (this);
    }

    public boolean isComplete() {
        // all fields are required to be filled.
        if (user_password.getString().length() == 0) {return false;}
        if (intrauterine_devices_qty.getString().length() == 0) {return false;}
        if (intrauterine_devices_price.getString().length() == 0) {return false;}
        if (intrauterine_devices_revenue.getString().length() == 0) {return false;}
        if (implants_qty.getString().length() == 0) {return false;}
        if (implants_price.getString().length() == 0) {return false;}
        if (implants_revenue.getString().length() == 0) {return false;}
        if (injections_qty.getString().length() == 0) {return false;}
        if (injections_price.getString().length() == 0) {return false;}
        if (injections_revenue.getString().length() == 0) {return false;}
        if (pills_qty.getString().length() == 0) {return false;}
        if (pills_price.getString().length() == 0) {return false;}
        if (pills_revenue.getString().length() == 0) {return false;}
        if (male_condoms_qty.getString().length() == 0) {return false;}
        if (male_condoms_price.getString().length() == 0) {return false;}
        if (male_condoms_revenue.getString().length() == 0) {return false;}
        if (female_condoms_qty.getString().length() == 0) {return false;}
        if (female_condoms_price.getString().length() == 0) {return false;}
        if (female_condoms_revenue.getString().length() == 0) {return false;}
        if (hiv_tests_qty.getString().length() == 0) {return false;}
        if (hiv_tests_price.getString().length() == 0) {return false;}
        if (hiv_tests_revenue.getString().length() == 0) {return false;}
        if (iud_removal_qty.getString().length() == 0) {return false;}
        if (iud_removal_price.getString().length() == 0) {return false;}
        if (iud_removal_revenue.getString().length() == 0) {return false;}
        if (implant_removal_qty.getString().length() == 0) {return false;}
        if (implant_removal_price.getString().length() == 0) {return false;}
        if (implant_removal_revenue.getString().length() == 0) {return false;}
        return true;
    }

    public boolean isValid() {
        return true;
    }

    public String toSMSFormat() {

        // msi financial username password month year
        // intrauterine_devices_qty intrauterine_devices_price intrauterine_devices_revenue
        // implants_qty implants_price implants_revenue
        // injections_qty injections_price injections_revenue pills_qty
        // pills_price pills_revenue male_condoms_qty male_condoms_price male_condoms_revenue
        // female_condoms_qty female_condoms_price female_condoms_revenue hiv_tests_qty
        // hiv_tests_price hiv_tests_revenue iud_removal_qty iud_removal_price
        // iud_removal_revenue implant_removal_qty implant_removal_price implant_removal_revenue

        String message;
        String username = config.get("user_name");
        String month = String.valueOf(monthField.getSelectedIndex());
        String year = yearField.getString(yearField.getSelectedIndex());

        message = "financial" + sep +
                username.replace(' ', Constants.CLEANER) + sep +
                user_password.getString().replace(' ', Constants.CLEANER) + sep +
                month + sep +
                year + sep +
                intrauterine_devices_qty.getString() + sep +
                intrauterine_devices_price.getString() + sep +
                intrauterine_devices_revenue.getString() + sep +
                implants_qty.getString() + sep +
                implants_price.getString() + sep +
                implants_revenue.getString() + sep +
                injections_qty.getString() + sep +
                injections_price.getString() + sep +
                injections_revenue.getString() + sep +
                pills_qty.getString() + sep +
                pills_price.getString() + sep +
                pills_revenue.getString() + sep +
                male_condoms_qty.getString() + sep +
                male_condoms_price.getString() + sep +
                male_condoms_revenue.getString() + sep +
                female_condoms_qty.getString() + sep +
                female_condoms_price.getString() + sep +
                female_condoms_revenue.getString() + sep +
                hiv_tests_qty.getString() + sep +
                hiv_tests_price.getString() + sep +
                hiv_tests_revenue.getString() + sep +
                iud_removal_qty.getString() + sep +
                iud_removal_price.getString() + sep +
                iud_removal_revenue.getString() + sep +
                implant_removal_qty.getString() + sep +
                implant_removal_price.getString() + sep +
                implant_removal_revenue.getString();

       return Constants.CODE_APP  + sep + message;
    }

    public String toText() {
        String sepa = "-";

        return "[Financier] " + monthField.getSelectedIndex()
                              + sepa + yearField.getSelectedIndex();
    }

    public void commandAction(Command c, Displayable d) {
        // help command displays Help Form.
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "mission");
            this.midlet.display.setCurrent(h);
        }

        // exit commands comes back to main menu.
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.midlet.mainMenu);
        }

        // save command
        if (c == CMD_SEND) {
            Alert alert;

            if (!this.isComplete()) {
                alert = new Alert("Données manquantes", "Tous les champs " +
                                  "requis doivent être remplis!", null,
                                   AlertType.ERROR);
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

            // sends the sms and reply feedback
            SMSSender sms = new SMSSender();
            String number = config.get("server_number");
            if (sms.send(number, this.toSMSFormat())) {
                alert = new Alert ("Demande envoyée !", "Vous allez recevoir" +
                                   " une confirmation du serveur.",
                                   null, AlertType.CONFIRMATION);
                this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
            } else {
               if (store.add(this.toText(), this.toSMSFormat())) {
                    alert = new Alert ("Échec d'envoi SMS", "Impossible d'envoyer" +
                                       " la demande par SMS. Le rapport a été enregistré dans le téléphone.", null,
                                       AlertType.WARNING);
                } else {
                    alert = new Alert ("Échec d'enregistrement", "Impossible d'envoyer ni d'enregistrer dans le téléphone.", null,
                                       AlertType.WARNING);
                }
                alert.setTimeout(Alert.FOREVER);
                this.midlet.startApp();
                this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
            }

        }
    }
}
