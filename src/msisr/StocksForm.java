
package msisr;

import javax.microedition.lcdui.*;
import msisr.Configuration.*;
import msisr.Constants.*;
import msisr.HelpForm.*;
import msisr.StocksReport.*;

/**
 * J2ME Stocks Form
 * Displays all fields required for under services section
 * Checks that those are all filled up
 * Checks for data errors
 * Saves into the DB.
 * @author rgaudin
 */
public class StocksForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Enreg.", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);
    private static final int MAX_SIZE = 5; // max no. of chars per field.
    private static final int MAX_OBS_SIZE = 50; // max no. of chars for observation fields

    public MSIMIDlet midlet;

    private Configuration config;

    private TextField intrauterine_devices_initial;
    private TextField intrauterine_devices_used;
    private TextField intrauterine_devices_lost;
    private TextField intrauterine_devices_received;
    private TextField implants_initial;
    private TextField implants_used;
    private TextField implants_lost;
    private TextField implants_received;
    private TextField injections_initial;
    private TextField injections_used;
    private TextField injections_lost;
    private TextField injections_received;
    private TextField pills_initial;
    private TextField pills_used;
    private TextField pills_lost;
    private TextField pills_received;
    private TextField male_condoms_initial;
    private TextField male_condoms_used;
    private TextField male_condoms_lost;
    private TextField male_condoms_received;
    private TextField female_condoms_initial;
    private TextField female_condoms_used;
    private TextField female_condoms_lost;
    private TextField female_condoms_received;
    private TextField hiv_tests_initial;
    private TextField hiv_tests_used;
    private TextField hiv_tests_lost;
    private TextField hiv_tests_received;
    private TextField pregnancy_tests_initial;
    private TextField pregnancy_tests_used;
    private TextField pregnancy_tests_lost;
    private TextField pregnancy_tests_received;

    private TextField intrauterine_devices_observation;
    private TextField implants_observation;
    private TextField injections_observation;
    private TextField pills_observation;
    private TextField male_condoms_observation;
    private TextField female_condoms_observation;
    private TextField hiv_tests_observation;
    private TextField pregnancy_tests_observation;

public StocksForm(MSIMIDlet midlet) {
    super("Rapport Stocks");
    this.midlet = midlet;

    config = new Configuration();

    // creating al fields (blank)
    intrauterine_devices_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    implants_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    implants_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    implants_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    implants_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    injections_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    injections_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    injections_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    injections_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    pills_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    pills_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    pills_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    pills_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    pregnancy_tests_initial = new TextField("Quantité initiale", null, MAX_SIZE, TextField.NUMERIC);
    pregnancy_tests_used = new TextField("Quantité utilisée", null, MAX_SIZE, TextField.NUMERIC);
    pregnancy_tests_lost = new TextField("Quantité perdue", null, MAX_SIZE, TextField.NUMERIC);
    pregnancy_tests_received = new TextField("Quantité reçue", null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    implants_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    injections_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    pills_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    male_condoms_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    female_condoms_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    hiv_tests_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);
    pregnancy_tests_observation = new TextField("Observations", null, MAX_OBS_SIZE, TextField.NON_PREDICTIVE);

    // if user requested to continue an existing report
    if (config.get("has_data").equalsIgnoreCase("true")) {
        // create an report object from store
        StocksReport report = new StocksReport();
        report.loadFromStore();

        // assign stored value to each fields.
        intrauterine_devices_initial.setString(valueForField(report.intrauterine_devices_initial));
        intrauterine_devices_used.setString(valueForField(report.intrauterine_devices_used));
        intrauterine_devices_lost.setString(valueForField(report.intrauterine_devices_lost));
        intrauterine_devices_received.setString(valueForField(report.intrauterine_devices_received));
        implants_initial.setString(valueForField(report.implants_initial));
        implants_used.setString(valueForField(report.implants_used));
        implants_lost.setString(valueForField(report.implants_lost));
        implants_received.setString(valueForField(report.implants_received));
        injections_initial.setString(valueForField(report.injections_initial));
        injections_used.setString(valueForField(report.injections_used));
        injections_lost.setString(valueForField(report.injections_lost));
        injections_received.setString(valueForField(report.injections_received));
        pills_initial.setString(valueForField(report.pills_initial));
        pills_used.setString(valueForField(report.pills_used));
        pills_lost.setString(valueForField(report.pills_lost));
        pills_received.setString(valueForField(report.pills_received));
        male_condoms_initial.setString(valueForField(report.male_condoms_initial));
        male_condoms_used.setString(valueForField(report.male_condoms_used));
        male_condoms_lost.setString(valueForField(report.male_condoms_lost));
        male_condoms_received.setString(valueForField(report.male_condoms_received));
        female_condoms_initial.setString(valueForField(report.female_condoms_initial));
        female_condoms_used.setString(valueForField(report.female_condoms_used));
        female_condoms_lost.setString(valueForField(report.female_condoms_lost));
        female_condoms_received.setString(valueForField(report.female_condoms_received));
        hiv_tests_initial.setString(valueForField(report.hiv_tests_initial));
        hiv_tests_used.setString(valueForField(report.hiv_tests_used));
        hiv_tests_lost.setString(valueForField(report.hiv_tests_lost));
        hiv_tests_received.setString(valueForField(report.hiv_tests_received));
        pregnancy_tests_initial.setString(valueForField(report.pregnancy_tests_initial));
        pregnancy_tests_used.setString(valueForField(report.pregnancy_tests_used));
        pregnancy_tests_lost.setString(valueForField(report.pregnancy_tests_lost));
        pregnancy_tests_received.setString(valueForField(report.pregnancy_tests_received));
        intrauterine_devices_observation.setString(stringValueForField(report.intrauterine_devices_observation));
        implants_observation.setString(stringValueForField(report.implants_observation));
        injections_observation.setString(stringValueForField(report.injections_observation));
        pills_observation.setString(stringValueForField(report.pills_observation));
        male_condoms_observation.setString(stringValueForField(report.male_condoms_observation));
        female_condoms_observation.setString(stringValueForField(report.female_condoms_observation));
        hiv_tests_observation.setString(stringValueForField(report.hiv_tests_observation));
        pregnancy_tests_observation.setString(stringValueForField(report.hiv_tests_observation));
    }

    // add fields to forms
    append("DIU (Unité)");
    append(intrauterine_devices_initial);
    append(intrauterine_devices_received);
    append(intrauterine_devices_used);
    append(intrauterine_devices_lost);
    append(intrauterine_devices_observation);

    append("Implant (Unité)");
    append(implants_initial);
    append(implants_received);
    append(implants_used);
    append(implants_lost);
    append(implants_observation);

    append("Injectable (Unité)");
    append(injections_initial);
    append(injections_received);
    append(injections_used);
    append(injections_lost);
    append(injections_observation);

    append("Pillule (cycle)");
    append(pills_initial);
    append(pills_received);
    append(pills_used);
    append(pills_lost);
    append(pills_observation);

    append("Préservatif masc. (Unité)");
    append(male_condoms_initial);
    append(male_condoms_received);
    append(male_condoms_used);
    append(male_condoms_lost);
    append(male_condoms_observation);

    append("Préservatif femi. (Unité)");
    append(female_condoms_initial);
    append(female_condoms_received);
    append(female_condoms_used);
    append(female_condoms_lost);
    append(female_condoms_observation);

    append("Détermine HIV (Unité)");
    append(hiv_tests_initial);
    append(hiv_tests_received);
    append(hiv_tests_used);
    append(hiv_tests_lost);
    append(hiv_tests_observation);

    append("Clearview (Unité)");
    append(pregnancy_tests_initial);
    append(pregnancy_tests_received);
    append(pregnancy_tests_used);
    append(pregnancy_tests_lost);
    append(pregnancy_tests_observation);

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
     * converts internal <code>String</code> data to <code>String</code> for field
     * @param value the number to display on field
     * @return the <code>String</code> to attach to the field.
     */
    private String stringValueForField(String value) {
        if (value == "-") {
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
        if (intrauterine_devices_initial.getString().length() == 0 ||
            intrauterine_devices_used.getString().length() == 0 ||
            intrauterine_devices_lost.getString().length() == 0 ||
            intrauterine_devices_received.getString().length() == 0 ||
            implants_initial.getString().length() == 0 ||
            implants_used.getString().length() == 0 ||
            implants_lost.getString().length() == 0 ||
            implants_received.getString().length() == 0 ||
            injections_initial.getString().length() == 0 ||
            injections_used.getString().length() == 0 ||
            injections_lost.getString().length() == 0 ||
            injections_received.getString().length() == 0 ||
            pills_initial.getString().length() == 0 ||
            pills_used.getString().length() == 0 ||
            pills_lost.getString().length() == 0 ||
            pills_received.getString().length() == 0 ||
            male_condoms_initial.getString().length() == 0 ||
            male_condoms_used.getString().length() == 0 ||
            male_condoms_lost.getString().length() == 0 ||
            male_condoms_received.getString().length() == 0 ||
            female_condoms_initial.getString().length() == 0 ||
            female_condoms_used.getString().length() == 0 ||
            female_condoms_lost.getString().length() == 0 ||
            female_condoms_received.getString().length() == 0 ||
            hiv_tests_initial.getString().length() == 0 ||
            hiv_tests_used.getString().length() == 0 ||
            hiv_tests_lost.getString().length() == 0 ||
            hiv_tests_received.getString().length() == 0 ||
            pregnancy_tests_initial.getString().length() == 0 ||
            pregnancy_tests_used.getString().length() == 0 ||
            pregnancy_tests_lost.getString().length() == 0 ||
            pregnancy_tests_received.getString().length() == 0) {
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
            StocksReport under_five = new StocksReport();
            under_five.setAll(Integer.parseInt(intrauterine_devices_initial.getString()),
                              Integer.parseInt(intrauterine_devices_used.getString()),
                              Integer.parseInt(intrauterine_devices_lost.getString()),
                              Integer.parseInt(intrauterine_devices_received.getString()),
                              Integer.parseInt(implants_initial.getString()),
                              Integer.parseInt(implants_used.getString()),
                              Integer.parseInt(implants_lost.getString()),
                              Integer.parseInt(implants_received.getString()),
                              Integer.parseInt(injections_initial.getString()),
                              Integer.parseInt(injections_used.getString()),
                              Integer.parseInt(injections_lost.getString()),
                              Integer.parseInt(injections_received.getString()),
                              Integer.parseInt(pills_initial.getString()),
                              Integer.parseInt(pills_used.getString()),
                              Integer.parseInt(pills_lost.getString()),
                              Integer.parseInt(pills_received.getString()),
                              Integer.parseInt(male_condoms_initial.getString()),
                              Integer.parseInt(male_condoms_used.getString()),
                              Integer.parseInt(male_condoms_lost.getString()),
                              Integer.parseInt(male_condoms_received.getString()),
                              Integer.parseInt(female_condoms_initial.getString()),
                              Integer.parseInt(female_condoms_used.getString()),
                              Integer.parseInt(female_condoms_lost.getString()),
                              Integer.parseInt(female_condoms_received.getString()),
                              Integer.parseInt(hiv_tests_initial.getString()),
                              Integer.parseInt(hiv_tests_used.getString()),
                              Integer.parseInt(hiv_tests_lost.getString()),
                              Integer.parseInt(hiv_tests_received.getString()),
                              Integer.parseInt(pregnancy_tests_initial.getString()),
                              Integer.parseInt(pregnancy_tests_used.getString()),
                              Integer.parseInt(pregnancy_tests_lost.getString()),
                              Integer.parseInt(pregnancy_tests_received.getString()),
                              intrauterine_devices_observation.getString(),
                              implants_observation.getString(),
                              injections_observation.getString(),
                              pills_observation.getString(),
                              male_condoms_observation.getString(),
                              female_condoms_observation.getString(),
                              hiv_tests_observation.getString(),
                              pregnancy_tests_observation.getString());
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
            alert = new Alert("Enregistré", "Les données de stocks ont été enregistrées", null, AlertType.CONFIRMATION);
            alert.setTimeout(Alert.FOREVER);
            this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
        }
    }
}
