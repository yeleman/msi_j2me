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


public class ServiceForm extends Form implements CommandListener {

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
    private TextField tubal_ligations;
    private TextField intrauterine_devices;
    private TextField injections;
    private TextField pills;
    private TextField male_condoms;
    private TextField female_condoms;
    private TextField emergency_controls;
    private TextField implants;
    private TextField new_clients;
    private TextField previous_clients;
    private TextField under25_visits;
    private TextField over25_visits;
    private TextField very_first_visits;
    private TextField short_term_method_visits;
    private TextField long_term_method_visits;
    private TextField hiv_counseling_clients;
    private TextField hiv_tests;
    private TextField hiv_positive_results;
    private TextField implant_removals;
    private TextField iud_removal;
    private TextField user_password;

    String sep = " ";

    // Ligature des trompes  (LMS)
    // Diapositif Intra-utérin (DIU)
    // Injectables - 3 mois
    // Plaquettes Pilules (payant)
    // Pièces Préservatifs masculins (payant)
    // Pièces Préservatifs féminins (payant)
    // Contraception d'urgence (payant)
    // Implant 5 ans
    // Total nouveaux clients PF
    // Total anciens clients PF
    // Nombre total de visites de clients âgés de 24 ans ou moins  pour services de PF
    // Nombre total de visites de clients de 25 ans ou plus pour services de PF
    // Nombre total de clients utilisant une méthode de PF pour la première fois dans leur vie
    // Nombre total de visites de clients pour méthode de PF à court terme
    // No. total de clients ayant recours à une méthode de PF méthode de longue durée
    // Nombre de clients ayant bénéficié du Counseling VIH
    // Nombre de clients ayant fait le test VIH
    // Nombre de clients dépistés séropositifs

    public ServiceForm(MsiMidlet midlet) {
        super("Service");
        this.midlet = midlet;
        config = new Configuration();
        store = new SMSStore();
        // periode
        monthField = new ChoiceGroup("Mois:", ChoiceGroup.POPUP, monthList, null);
        yearField = new ChoiceGroup("Année:", ChoiceGroup.POPUP, yearList, null);

        user_password = new TextField("Mot de passe:", null, 20, TextField.ANY);
        // CAP-providing services
        tubal_ligations = new TextField("Ligature des trompes (LMS)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        intrauterine_devices = new TextField("Diapositif Intra-utérin (DIU)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        injections = new TextField("Injectables", null, Constants.MAX_SIZE, TextField.DECIMAL);
        pills = new TextField("Plaquettes Pilules (payant)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        male_condoms = new TextField("Masculins (payant)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        female_condoms = new TextField("Féminins (payant)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        emergency_controls = new TextField("Contraception d'urgence (payant)", null, Constants.MAX_SIZE, TextField.DECIMAL);
        implants = new TextField("Implant 5 ans", null, Constants.MAX_SIZE, TextField.DECIMAL);
        // Clients related services
        new_clients = new TextField("Nouveaux clients", null, Constants.MAX_SIZE, TextField.DECIMAL);
        previous_clients = new TextField("Anciens clients", null, Constants.MAX_SIZE, TextField.DECIMAL);
        under25_visits = new TextField("âgés de 24 ans ou moins", null, Constants.MAX_SIZE, TextField.DECIMAL);
        over25_visits = new TextField("de 25 ans ou plus", null, Constants.MAX_SIZE, TextField.DECIMAL);
        very_first_visits = new TextField("Utilisant une méthode de PF pour la première fois", null, Constants.MAX_SIZE, TextField.DECIMAL);
        short_term_method_visits = new TextField("Méthode de PF à court terme", null, Constants.MAX_SIZE, TextField.DECIMAL);
        long_term_method_visits = new TextField("Méthode de PF méthode de longue durée", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_counseling_clients = new TextField("Bénéficié du Counseling VIH", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_tests = new TextField("test VIH", null, Constants.MAX_SIZE, TextField.DECIMAL);
        hiv_positive_results = new TextField("Dépistés séropositifs", null, Constants.MAX_SIZE, TextField.DECIMAL);
        // non-CAP providing services
        implant_removals = new TextField("Retrait d'implants", null, Constants.MAX_SIZE, TextField.DECIMAL);
        iud_removal = new TextField("Retrait de DIU", null, Constants.MAX_SIZE, TextField.DECIMAL);

        append(monthField);
        append(yearField);
        append(tubal_ligations);
        append(intrauterine_devices);
        append(injections);
        append(pills);
        append("Pièces Préservatifs");
        append(male_condoms);
        append(female_condoms);
        append(emergency_controls);
        append(implants);
        append(new_clients);
        append(previous_clients);
        append(under25_visits);
        append(over25_visits);
        append(very_first_visits);
        append(short_term_method_visits);
        append(long_term_method_visits);
        append(hiv_counseling_clients);
        append(hiv_tests);
        append(hiv_positive_results);
        append(implant_removals);
        append(iud_removal);
        append(user_password);

        addCommand(CMD_EXIT);
        addCommand(CMD_SEND);
        addCommand(CMD_HELP);
        this.setCommandListener (this);
    }

    public boolean isComplete() {
        // all fields are required to be filled.
        if (user_password.getString().length() == 0) {return false;}
        if (tubal_ligations.getString().length() == 0){return false;}
        if (intrauterine_devices.getString().length() == 0){return false;}
        if (injections.getString().length() == 0){return false;}
        if (pills.getString().length() == 0){return false;}
        if (male_condoms.getString().length() == 0){return false;}
        if (female_condoms.getString().length() == 0){return false;}
        if (emergency_controls.getString().length() == 0){return false;}
        if (implants.getString().length() == 0){return false;}
        if (new_clients.getString().length() == 0){return false;}
        if (previous_clients.getString().length() == 0){return false;}
        if (under25_visits.getString().length() == 0){return false;}
        if (over25_visits.getString().length() == 0){return false;}
        if (very_first_visits .getString().length() == 0){return false;}
        if (short_term_method_visits .getString().length() == 0){return false;}
        if (long_term_method_visits.getString().length() == 0){return false;}
        if (hiv_counseling_clients .getString().length() == 0){return false;}
        if (hiv_tests.getString().length() == 0){return false;}
        if (hiv_positive_results.getString().length() == 0){return false;}
        if (implant_removals.getString().length() == 0){return false;}
        if (iud_removal.getString().length() == 0){return false;}
        return true;
    }

    public boolean isValid() {
        return true;
    }

    public String toSMSFormat() {

        // msi services username password month year
        // tubal_ligations intrauterine_devices injections pills
        // male_condoms female_condoms emergency_controls implants
        // new_clients previous_clients under25_visits over25_visits
        // very_first_visits short_term_method_visits long_term_method_visits
        // hiv_counseling_clients hiv_tests hiv_positive_results
        // implant_removals iud_removal

        String message;
        String username = config.get("user_name");
        String month = String.valueOf(monthField.getSelectedIndex());
        String year = yearField.getString(yearField.getSelectedIndex());

        message = "services" + sep +
                  username.replace(' ', Constants.CLEANER) + sep +
                  user_password.getString().replace(' ', Constants.CLEANER) + sep +
                  month + sep +
                  year + sep +
                  tubal_ligations.getString() + sep +
                  intrauterine_devices.getString() + sep +
                  injections.getString() + sep +
                  pills.getString() + sep +
                  male_condoms.getString() + sep +
                  female_condoms.getString() + sep +
                  emergency_controls.getString() + sep +
                  implants.getString() + sep +
                  new_clients.getString() + sep +
                  previous_clients.getString() + sep +
                  under25_visits.getString() + sep +
                  over25_visits.getString() + sep +
                  very_first_visits .getString() + sep +
                  short_term_method_visits .getString() + sep +
                  long_term_method_visits.getString() + sep +
                  hiv_counseling_clients .getString() + sep +
                  hiv_tests.getString() + sep +
                  hiv_positive_results.getString() + sep +
                  implant_removals.getString() + sep +
                  iud_removal.getString();

       return Constants.CODE_APP  + sep + message;
    }

    public String toText() {
        String sepa = "-";

        return "[Service] " + monthField.getSelectedIndex()
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
