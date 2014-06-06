package msi_sr;

import javax.microedition.lcdui.*;

import msi_sr.Configuration.*;
import msi_sr.Constants.*;
import msi_sr.SharedChecks.*;
import msi_sr.FinancialReport.*;

/**
 * J2ME Form displaying a long help text
 * Instanciated with a section paramater
 * which triggers appropriate text.
 * @author Fad
 */


public class FinancialForm extends Form implements CommandListener {

    private static final Command CMD_EXIT = new Command ("Retour", Command.BACK, 1);
    private static final Command CMD_SAVE = new Command ("Envoi.", Command.OK, 1);
    private static final Command CMD_HELP = new Command ("Aide", Command.HELP, 2);

    MsiMIDlet midlet;

    private String ErrorMessage = "";

    private Configuration config;

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


    public FinancialForm(MsiMIDlet midlet) {
        super("Financial");
        this.midlet = midlet;

        config = new Configuration();
        // periode

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

        // if user requested to continue an existing report
        if (config.get("has_data").equalsIgnoreCase("true")) {
            // create an report object from store
            FinancialReport report = new FinancialReport();
            report.loadFromStore();

            // assign stored value to each fields.
            intrauterine_devices_qty.setString(valueForField(report.intrauterine_devices_qty));
            intrauterine_devices_price.setString(valueForField(report.intrauterine_devices_price));
            intrauterine_devices_revenue.setString(valueForField(report.implant_removal_revenue));
            implants_qty.setString(valueForField(report.implants_qty));
            implants_price.setString(valueForField(report.implants_price));
            implants_revenue.setString(valueForField(report.injections_revenue));
            injections_qty.setString(valueForField(report.injections_qty));
            injections_price.setString(valueForField(report.injections_price));
            injections_revenue.setString(valueForField(report.pills_revenue));
            pills_qty.setString(valueForField(report.pills_qty));
            pills_price.setString(valueForField(report.pills_price));
            pills_revenue.setString(valueForField(report.pills_revenue));
            male_condoms_qty.setString(valueForField(report.male_condoms_qty));
            male_condoms_price.setString(valueForField(report.male_condoms_price));
            male_condoms_revenue.setString(valueForField(report.male_condoms_revenue));
            female_condoms_qty.setString(valueForField(report.female_condoms_qty));
            female_condoms_price.setString(valueForField(report.female_condoms_price));
            female_condoms_revenue.setString(valueForField(report.female_condoms_revenue));
            hiv_tests_qty.setString(valueForField(report.hiv_tests_qty));
            hiv_tests_price.setString(valueForField(report.hiv_tests_price));
            hiv_tests_revenue.setString(valueForField(report.hiv_tests_revenue));
            iud_removal_qty.setString(valueForField(report.iud_removal_qty));
            iud_removal_price.setString(valueForField(report.iud_removal_price));
            iud_removal_revenue.setString(valueForField(report.iud_removal_revenue));
            implant_removal_qty.setString(valueForField(report.implant_removal_qty));
            implant_removal_price.setString(valueForField(report.implant_removal_price));
            implant_removal_revenue.setString(valueForField(report.implant_removal_revenue));
        }

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
        addCommand(CMD_SAVE);
        addCommand(CMD_HELP);
        this.setCommandListener (this);
    }

    public boolean isComplete() {
        // all fields are required to be filled.
        if (user_password.getString().length() == 0 ||
            intrauterine_devices_qty.getString().length() == 0 ||
            intrauterine_devices_price.getString().length() == 0 ||
            intrauterine_devices_revenue.getString().length() == 0 ||
            implants_qty.getString().length() == 0 ||
            implants_price.getString().length() == 0 ||
            implants_revenue.getString().length() == 0 ||
            injections_qty.getString().length() == 0 ||
            injections_price.getString().length() == 0 ||
            injections_revenue.getString().length() == 0 ||
            pills_qty.getString().length() == 0 ||
            pills_price.getString().length() == 0 ||
            pills_revenue.getString().length() == 0 ||
            male_condoms_qty.getString().length() == 0 ||
            male_condoms_price.getString().length() == 0 ||
            male_condoms_revenue.getString().length() == 0 ||
            female_condoms_qty.getString().length() == 0 ||
            female_condoms_price.getString().length() == 0 ||
            female_condoms_revenue.getString().length() == 0 ||
            hiv_tests_qty.getString().length() == 0 ||
            hiv_tests_price.getString().length() == 0 ||
            hiv_tests_revenue.getString().length() == 0 ||
            iud_removal_qty.getString().length() == 0 ||
            iud_removal_price.getString().length() == 0 ||
            iud_removal_revenue.getString().length() == 0 ||
            implant_removal_qty.getString().length() == 0 ||
            implant_removal_price.getString().length() == 0 ||
            implant_removal_revenue.getString().length() == 0) {return false;}
        return true;
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

    //     // msi financial username password month year
    //     // intrauterine_devices_qty intrauterine_devices_price intrauterine_devices_revenue
    //     // implants_qty implants_price implants_revenue
    //     // injections_qty injections_price injections_revenue pills_qty
    //     // pills_price pills_revenue male_condoms_qty male_condoms_price male_condoms_revenue
    //     // female_condoms_qty female_condoms_price female_condoms_revenue hiv_tests_qty
    //     // hiv_tests_price hiv_tests_revenue iud_removal_qty iud_removal_price
    //     // iud_removal_revenue implant_removal_qty implant_removal_price implant_removal_revenue


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

            if (!this.isComplete()) {
                alert = new Alert("Données manquantes", "Tous les champs " +
                                  "requis doivent être remplis!", null,
                                   AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                this.midlet.display.setCurrent (alert, this);
                return;
            }

            FinancialReport financial = new FinancialReport();
            financial.setAll(Integer.parseInt(intrauterine_devices_qty.getString()),
                            Integer.parseInt(intrauterine_devices_price.getString()),
                            Integer.parseInt(intrauterine_devices_revenue.getString()),
                            Integer.parseInt(implants_qty.getString()),
                            Integer.parseInt(implants_price.getString()),
                            Integer.parseInt(implants_revenue.getString()),
                            Integer.parseInt(injections_qty.getString()),
                            Integer.parseInt(injections_price.getString()),
                            Integer.parseInt(injections_revenue.getString()),
                            Integer.parseInt(pills_qty.getString()),
                            Integer.parseInt(pills_price.getString()),
                            Integer.parseInt(pills_revenue.getString()),
                            Integer.parseInt(male_condoms_qty.getString()),
                            Integer.parseInt(male_condoms_price.getString()),
                            Integer.parseInt(male_condoms_revenue.getString()),
                            Integer.parseInt(female_condoms_qty.getString()),
                            Integer.parseInt(female_condoms_price.getString()),
                            Integer.parseInt(female_condoms_revenue.getString()),
                            Integer.parseInt(hiv_tests_qty.getString()),
                            Integer.parseInt(hiv_tests_price.getString()),
                            Integer.parseInt(hiv_tests_revenue.getString()),
                            Integer.parseInt(iud_removal_qty.getString()),
                            Integer.parseInt(iud_removal_price.getString()),
                            Integer.parseInt(iud_removal_revenue.getString()),
                            Integer.parseInt(implant_removal_qty.getString()),
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
            alert = new Alert("Enregistré", "Les données des 5ans et + ont été enregistrées", null, AlertType.CONFIRMATION);
            alert.setTimeout(Alert.FOREVER);
            this.midlet.display.setCurrent (alert, this.midlet.mainMenu);
        }
    }
}
