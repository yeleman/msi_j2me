
package msisr;

import javax.microedition.lcdui.*;
import msisr.Configuration.*;
import msisr.HelpForm.*;
import msisr.ProvidedServicesReport.*;

/**
 * J2ME ProvidedServices Form
 * Displays all fields required for under services section
 * Checks that those are all filled up
 * Checks for data errors
 * Saves into the DB.
 * @author rgaudin
 */
public class ProvidedServicesForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Enreg.", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);
    private static final int MAX_SIZE = 5; // max no. of chars per field.

    public MSIMIDlet midlet;

    private Configuration config;

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

public ProvidedServicesForm(MSIMIDlet midlet) {
    super("Services Prestés");
    this.midlet = midlet;

    config = new Configuration();

    // CAP-providing services
    tubal_ligations = new TextField("Ligature des trompes (LMS)", null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices = new TextField("Diapositif Intra-utérin (DIU)", null, MAX_SIZE, TextField.NUMERIC);
    injections = new TextField("Injectables", null, MAX_SIZE, TextField.NUMERIC);
    pills = new TextField("Plaquettes Pilules (payant)", null, MAX_SIZE, TextField.NUMERIC);
    male_condoms = new TextField("Masculins (payant)", null, MAX_SIZE, TextField.NUMERIC);
    female_condoms = new TextField("Féminins (payant)", null, MAX_SIZE, TextField.NUMERIC);
    emergency_controls = new TextField("Contraception d'urgence (payant)", null, MAX_SIZE, TextField.NUMERIC);
    implants = new TextField("Implant 5 ans", null, MAX_SIZE, TextField.NUMERIC);
    // Clients related services
    new_clients = new TextField("Nouveaux clients", null, MAX_SIZE, TextField.NUMERIC);
    previous_clients = new TextField("Anciens clients", null, MAX_SIZE, TextField.NUMERIC);
    under25_visits = new TextField("âgés de 24 ans ou moins", null, MAX_SIZE, TextField.NUMERIC);
    over25_visits = new TextField("de 25 ans ou plus", null, MAX_SIZE, TextField.NUMERIC);
    very_first_visits = new TextField("Utilisant une méthode de PF pour la première fois", null, MAX_SIZE, TextField.NUMERIC);
    short_term_method_visits = new TextField("Méthode de PF à court terme", null, MAX_SIZE, TextField.NUMERIC);
    long_term_method_visits = new TextField("Méthode de PF méthode de longue durée", null, MAX_SIZE, TextField.NUMERIC);
    hiv_counseling_clients = new TextField("Bénéficié du Counseling VIH", null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests = new TextField("test VIH", null, MAX_SIZE, TextField.NUMERIC);
    hiv_positive_results = new TextField("Dépistés séropositifs", null, MAX_SIZE, TextField.NUMERIC);
    // non-CAP providing services
    implant_removals = new TextField("Retrait d'implants", null, MAX_SIZE, TextField.NUMERIC);
    iud_removal = new TextField("Retrait de DIU", null, MAX_SIZE, TextField.NUMERIC);

    // if user requested to continue an existing report
    if (config.get("has_data").equalsIgnoreCase("true")) {
        // create an report object from store
        ProvidedServicesReport report = new ProvidedServicesReport();
        report.loadFromStore();

        // assign stored value to each fields.
        tubal_ligations.setString(valueForField(report.tubal_ligations));
        intrauterine_devices.setString(valueForField(report.intrauterine_devices));
        injections.setString(valueForField(report.injections));
        pills.setString(valueForField(report.pills));
        male_condoms.setString(valueForField(report.male_condoms));
        female_condoms.setString(valueForField(report.female_condoms));
        emergency_controls.setString(valueForField(report.emergency_controls));
        implants.setString(valueForField(report.implants));
        new_clients.setString(valueForField(report.new_clients));
        previous_clients.setString(valueForField(report.previous_clients));
        under25_visits.setString(valueForField(report.under25_visits));
        over25_visits.setString(valueForField(report.over25_visits));
        very_first_visits.setString(valueForField(report.very_first_visits));
        short_term_method_visits.setString(valueForField(report.short_term_method_visits));
        long_term_method_visits.setString(valueForField(report.long_term_method_visits));
        hiv_counseling_clients.setString(valueForField(report.hiv_counseling_clients));
        hiv_tests.setString(valueForField(report.hiv_tests));
        hiv_positive_results.setString(valueForField(report.hiv_positive_results));
        implant_removals.setString(valueForField(report.implant_removals));
        iud_removal.setString(valueForField(report.iud_removal));
    }

    // add fields to forms
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

    addCommand(CMD_EXIT);
    addCommand(CMD_SAVE);
    addCommand(CMD_HELP);
    this.setCommandListener (this);
}

    /*
     * converts internal <code>int</code> data to <code>String</code> for field
     * @param value the number to display on field
     * @return the <code>String</code> to attach to the field.
     */
    private String valueForField(int value) {
        if (value == -1) {
            return "";
        }
        return String.valueOf(value);
    }

    /*
     * Whether all required fields are filled
     * @return <code>true</code> is all fields are filled
     * <code>false</code> otherwise.
     */
    public boolean isComplete() {

        // all fields are required to be filled.
        if (tubal_ligations.getString().length() == 0 ||
            intrauterine_devices.getString().length() == 0 ||
            injections.getString().length() == 0 ||
            pills.getString().length() == 0 ||
            male_condoms.getString().length() == 0 ||
            female_condoms.getString().length() == 0 ||
            emergency_controls.getString().length() == 0 ||
            implants.getString().length() == 0 ||
            new_clients.getString().length() == 0 ||
            previous_clients.getString().length() == 0 ||
            under25_visits.getString().length() == 0 ||
            over25_visits.getString().length() == 0 ||
            very_first_visits .getString().length() == 0 ||
            short_term_method_visits .getString().length() == 0 ||
            long_term_method_visits.getString().length() == 0 ||
            hiv_counseling_clients .getString().length() == 0 ||
            hiv_tests.getString().length() == 0 ||
            hiv_positive_results.getString().length() == 0 ||
            implant_removals.getString().length() == 0 ||
            iud_removal.getString().length() == 0) {
            return false;
        }
        return true;
    }

    public void commandAction(Command c, Displayable d) {
        // help command displays Help Form.
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "under_five");
            this.midlet.display.setCurrent(h);
        }

        // exit commands comes back to main menu.
        if (c == CMD_EXIT) {
            this.midlet.display.setCurrent(this.midlet.mainMenu);
        }

        // save command
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


            // create a report object from values
            ProvidedServicesReport under_five = new ProvidedServicesReport();
            under_five.setAll(Integer.parseInt(tubal_ligations.getString()),
                              Integer.parseInt(intrauterine_devices.getString()),
                              Integer.parseInt(injections.getString()),
                              Integer.parseInt(pills.getString()),
                              Integer.parseInt(male_condoms.getString()),
                              Integer.parseInt(female_condoms.getString()),
                              Integer.parseInt(emergency_controls.getString()),
                              Integer.parseInt(implants.getString()),
                              Integer.parseInt(new_clients.getString()),
                              Integer.parseInt(previous_clients.getString()),
                              Integer.parseInt(under25_visits.getString()),
                              Integer.parseInt(over25_visits.getString()),
                              Integer.parseInt(very_first_visits .getString()),
                              Integer.parseInt(short_term_method_visits .getString()),
                              Integer.parseInt(long_term_method_visits.getString()),
                              Integer.parseInt(hiv_counseling_clients .getString()),
                              Integer.parseInt(hiv_tests.getString()),
                              Integer.parseInt(hiv_positive_results.getString()),
                              Integer.parseInt(implant_removals.getString()),
                              Integer.parseInt(iud_removal.getString())
            );

            // check for errors and display first error
            if (!under_five.dataIsValid()) {
                alert = new Alert("Données incorrectes!", under_five.errorMessage(), null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // data appears to be valid now. Let's save it.
            under_five.saveInStore();
            // refresh menu as we've changed data.
            this.midlet.refreshMenu();

            // mark report in progress
            config.set("has_data", "true");

            // Confirm data is OK and go to main menu
            alert = new Alert("Enregistré", "Les données des services prestés été enregistrées", null, AlertType.CONFIRMATION);
            alert.setTimeout(Alert.FOREVER);
            this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
        }
    }
}
