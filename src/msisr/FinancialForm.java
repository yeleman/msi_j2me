
package msisr;

import javax.microedition.lcdui.*;
import msisr.Configuration.*;
import msisr.Constants.*;
import msisr.HelpForm.*;
import msisr.FinancialReport.*;

/**
 * J2ME ProvidedServices Form
 * Displays all fields required for under services section
 * Checks that those are all filled up
 * Checks for data errors
 * Saves into the DB.
 * @author rgaudin
 */
public class FinancialForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Enreg.", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);
    private static final int MAX_SIZE = 5; // max no. of chars per field.

    private static final String PRICE_TXT = "Prix Unitaire";
    private static final String REVENUE_TXT = "Revenu mensuel net";

    public MSIMIDlet midlet;

    private Configuration config;

    private TextField intrauterine_devices_price;
    private TextField intrauterine_devices_revenue;
    private TextField implants_price;
    private TextField implants_revenue;
    private TextField injections_price;
    private TextField injections_revenue;
    private TextField pills_price;
    private TextField pills_revenue;
    private TextField male_condoms_price;
    private TextField male_condoms_revenue;
    private TextField female_condoms_price;
    private TextField female_condoms_revenue;
    private TextField hiv_tests_price;
    private TextField hiv_tests_revenue;
    private TextField iud_removal_price;
    private TextField iud_removal_revenue;
    private TextField implant_removal_price;
    private TextField implant_removal_revenue;

public FinancialForm(MSIMIDlet midlet) {
    super("Rapport Financier");
    this.midlet = midlet;

    config = new Configuration();

    // creating al fields (blank)
    intrauterine_devices_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    intrauterine_devices_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    implants_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    implants_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    injections_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    injections_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    pills_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    pills_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    male_condoms_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    female_condoms_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    hiv_tests_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    iud_removal_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    iud_removal_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    implant_removal_price = new TextField(PRICE_TXT, null, MAX_SIZE, TextField.NUMERIC);
    implant_removal_revenue = new TextField(REVENUE_TXT, null, MAX_SIZE, TextField.NUMERIC);

    // if user requested to continue an existing report
    if (config.get("has_data").equalsIgnoreCase("true")) {
        // create an report object from store
        FinancialReport report = new FinancialReport();
        report.loadFromStore();

        // assign stored value to each fields.
        intrauterine_devices_price.setString(valueForField(report.intrauterine_devices_price));
        intrauterine_devices_revenue.setString(valueForField(report.intrauterine_devices_revenue));
        implants_price.setString(valueForField(report.implants_price));
        implants_revenue.setString(valueForField(report.implants_revenue));
        injections_price.setString(valueForField(report.injections_price));
        injections_revenue.setString(valueForField(report.injections_revenue));
        pills_price.setString(valueForField(report.pills_price));
        pills_revenue.setString(valueForField(report.pills_revenue));
        male_condoms_price.setString(valueForField(report.male_condoms_price));
        male_condoms_revenue.setString(valueForField(report.male_condoms_revenue));
        female_condoms_price.setString(valueForField(report.female_condoms_price));
        female_condoms_revenue.setString(valueForField(report.female_condoms_revenue));
        hiv_tests_price.setString(valueForField(report.hiv_tests_price));
        hiv_tests_revenue.setString(valueForField(report.hiv_tests_revenue));
        iud_removal_price.setString(valueForField(report.iud_removal_price));
        iud_removal_revenue.setString(valueForField(report.iud_removal_revenue));
        implant_removal_price.setString(valueForField(report.implant_removal_price));
        implant_removal_revenue.setString(valueForField(report.implant_removal_revenue));
    }

    // add fields to forms
    append("DIU");
    append(intrauterine_devices_price);
    append(intrauterine_devices_revenue);
    append("Implant/Jadelle");
    append(implants_price);
    append(implants_revenue);
    append("Injectables");
    append(injections_price);
    append(injections_revenue);
    append("Pillules");
    append(pills_price);
    append(pills_revenue);
    append("Préservatif Masc.");
    append(male_condoms_price);
    append(male_condoms_revenue);
    append("Préservatif Femi.");
    append(female_condoms_price);
    append(female_condoms_revenue);
    append("Tests VIH");
    append(hiv_tests_price);
    append(hiv_tests_revenue);
    append("Retraits DIU");
    append(iud_removal_price);
    append(iud_removal_revenue);
    append("Retraits Implants");
    append(implant_removal_price);
    append(implant_removal_revenue);

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
        if (intrauterine_devices_price.getString().length() == 0 ||
            intrauterine_devices_revenue.getString().length() == 0 ||
            implants_price.getString().length() == 0 ||
            implants_revenue.getString().length() == 0 ||
            injections_price.getString().length() == 0 ||
            injections_revenue.getString().length() == 0 ||
            pills_price.getString().length() == 0 ||
            pills_revenue.getString().length() == 0 ||
            male_condoms_price.getString().length() == 0 ||
            male_condoms_revenue.getString().length() == 0 ||
            female_condoms_price.getString().length() == 0 ||
            female_condoms_revenue.getString().length() == 0 ||
            hiv_tests_price.getString().length() == 0 ||
            hiv_tests_revenue.getString().length() == 0 ||
            iud_removal_price.getString().length() == 0 ||
            iud_removal_revenue.getString().length() == 0 ||
            implant_removal_price.getString().length() == 0 ||
            implant_removal_revenue.getString().length() == 0) {
            return false;
        }
        return true;
    }

    public void commandAction(Command c, Displayable d) {
        // help command displays Help Form.
        if (c == CMD_HELP) {
            HelpForm h = new HelpForm(this.midlet, this, "financial");
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
            FinancialReport financial = new FinancialReport();
            financial.setAll(Integer.parseInt(intrauterine_devices_price.getString()),
                             Integer.parseInt(intrauterine_devices_revenue.getString()),
                             Integer.parseInt(implants_price.getString()),
                             Integer.parseInt(implants_revenue.getString()),
                             Integer.parseInt(injections_price.getString()),
                             Integer.parseInt(injections_revenue.getString()),
                             Integer.parseInt(pills_price.getString()),
                             Integer.parseInt(pills_revenue.getString()),
                             Integer.parseInt(male_condoms_price.getString()),
                             Integer.parseInt(male_condoms_revenue.getString()),
                             Integer.parseInt(female_condoms_price.getString()),
                             Integer.parseInt(female_condoms_revenue.getString()),
                             Integer.parseInt(hiv_tests_price.getString()),
                             Integer.parseInt(hiv_tests_revenue.getString()),
                             Integer.parseInt(iud_removal_price.getString()),
                             Integer.parseInt(iud_removal_revenue.getString()),
                             Integer.parseInt(implant_removal_price.getString()),
                             Integer.parseInt(implant_removal_revenue.getString()));
            // check for errors and display first error
            if (!financial.dataIsValid()) {
                alert = new Alert("Données incorrectes!", financial.errorMessage(), null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            // data appears to be valid now. Let's save it.
            financial.saveInStore();
            // refresh menu as we've changed data.
            this.midlet.refreshMenu();

            // mark report in progress
            config.set("has_data", "true");

            // Confirm data is OK and go to main menu
            alert = new Alert("Enregistré", "Les données des services prestés ont été enregistrées", null, AlertType.CONFIRMATION);
            alert.setTimeout(Alert.FOREVER);
            this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
        }
    }
}
