
package msisr;

import java.util.*;
import msisr.ProvidedServicesReport.*;
import msisr.FinancialReport.*;
import msisr.StocksReport.*;

/**
 * Holder for MSIReport Parts.
 * @author rgaudin
 */
public class MSIReport {

    private Vector _errors = new Vector();

    public ProvidedServicesReport provided_services;
    public FinancialReport financial;
    public StocksReport stocks;
    public String username;
    public String password;
    public int month;
    public int year;

    public MSIReport() {
        // load all parts
        provided_services = new ProvidedServicesReport();
        provided_services.loadFromStore();

        financial = new FinancialReport();
        financial.loadFromStore();

        stocks = new StocksReport();
        stocks.loadFromStore();
    }


    /*
     * generates a string to be used in SMS
     * @return long <code>String</true> describing all data
     * formatted for SMS sending.
     */
    public String toSMSFormat() {
        String sep = " ";
        return "msi" + sep + username + sep + password + sep + month + sep + year + sep +
               provided_services.toSMSFormat() + sep + financial.toSMSFormat() + sep +
               stocks.toSMSFormat();
    }

    /*
     * checks whether data is legit
     * checks all logic algorithms and fills the <code>_errors</code> variable.
     * @return <code>true</true> if all data is valid
     * <code>false</code> otherwise.
     */
    public boolean dataIsValid(boolean only_reports) {

        _errors = new Vector();

        // verify that all parts are present
        if (!provided_services.dataIsValid()) {
            _errors.addElement("Les données de Services Prestés sont incomplètes ou erronées");
        }

        if (!financial.dataIsValid()) {
            _errors.addElement("Les données financières sont incomplètes ou erronées");
        }

        if (!stocks.dataIsValid()) {
            _errors.addElement("Les données de stocks sont incomplètes ou erronées");
        }

        // IUD
        if (provided_services.intrauterine_devices != financial.intrauterine_devices_qty) {
            _errors.addElement("DIU: Quantité prestés ("+ provided_services.intrauterine_devices + ") différent de Quantité financière ("+ financial.intrauterine_devices_qty +").");
        }
        if (provided_services.intrauterine_devices != stocks.intrauterine_devices_used) {
            _errors.addElement("DIU: Quantité prestés ("+ provided_services.intrauterine_devices + ") différent de Quantité stocks ("+ stocks.intrauterine_devices_used +").");
        }

        // Implant
        if (provided_services.implants != financial.implants_qty) {
            _errors.addElement("Implant: Quantité prestés ("+ provided_services.implants + ") différent de Quantité financière ("+ financial.implants_qty +").");
        }
        if (provided_services.implants != stocks.implants_used) {
            _errors.addElement("Implant: Quantité prestés ("+ provided_services.implants + ") différent de Quantité stocks ("+ stocks.implants_used +").");
        }

        // Injections
        if (provided_services.injections != financial.injections_qty) {
            _errors.addElement("Injectable: Quantité prestés ("+ provided_services.injections + ") différent de Quantité financière ("+ financial.injections_qty +").");
        }
        if (provided_services.injections != stocks.injections_used) {
            _errors.addElement("Injectable: Quantité prestés ("+ provided_services.injections + ") différent de Quantité stocks ("+ stocks.injections_used +").");
        }

        // Pills
        if (provided_services.pills != financial.pills_qty) {
            _errors.addElement("Pilule: Quantité prestés ("+ provided_services.pills + ") différent de Quantité financière ("+ financial.pills_qty +").");
        }
        if (provided_services.pills != stocks.pills_used) {
            _errors.addElement("Pilule: Quantité prestés ("+ provided_services.pills + ") différent de Quantité stocks ("+ stocks.pills_used +").");
        }

        // Male condoms
        if (provided_services.male_condoms != financial.male_condoms_qty) {
            _errors.addElement("Préservatif masc.: Quantité prestés ("+ provided_services.male_condoms + ") différent de Quantité financière ("+ financial.male_condoms_qty +").");
        }
        if (provided_services.male_condoms != stocks.male_condoms_used) {
            _errors.addElement("Préservatif masc.: Quantité prestés ("+ provided_services.male_condoms + ") différent de Quantité stocks ("+ stocks.male_condoms_used +").");
        }

        // Female condoms
        if (provided_services.female_condoms != financial.female_condoms_qty) {
            _errors.addElement("Préservatif femin.: Quantité prestés ("+ provided_services.female_condoms + ") différent de Quantité financière ("+ financial.female_condoms_qty +").");
        }
        if (provided_services.female_condoms != stocks.female_condoms_used) {
            _errors.addElement("Préservatif femin.: Quantité prestés ("+ provided_services.female_condoms + ") différent de Quantité stocks ("+ stocks.female_condoms_used +").");
        }

        // HIV Tests
        if (provided_services.hiv_tests != financial.hiv_tests_qty) {
            _errors.addElement("Test VIH: Quantité prestés ("+ provided_services.hiv_tests + ") différent de Quantité financière ("+ financial.hiv_tests_qty +").");
        }
        if (provided_services.hiv_tests != stocks.hiv_tests_used) {
            _errors.addElement("Test VIH: Quantité prestés ("+ provided_services.hiv_tests + ") différent de Quantité stocks ("+ stocks.hiv_tests_used +").");
        }

        // IUD Removal
        if (provided_services.iud_removal != financial.iud_removal_qty) {
            _errors.addElement("Retrait DIU: Quantité prestés ("+ provided_services.iud_removal + ") différent de Quantité financière ("+ financial.iud_removal_qty +").");
        }

        // Implant Removal
        if (provided_services.implant_removals != financial.implant_removal_qty) {
            _errors.addElement("Retrait Implant: Quantité prestés ("+ provided_services.implant_removals + ") différent de Quantité financière ("+ financial.implant_removal_qty +").");
        }

        if (!only_reports) {

            // verify that meta data is present
            if (!(username.length() >= Constants.username_min_length)) {
                _errors.addElement("L'identifiant semble incorrect.");
            }

            if (!(password.length() >= Constants.password_min_length)) {
                _errors.addElement("Le mot de passe semble incorrect.");
            }

            if (month < 1 || month > 12) {
                _errors.addElement("Le mois du rapport est incorrect.");
            }

            if (year < 2011 || year > 2020) {
                _errors.addElement("L'année du rapport est incorrecte.");
            }
        }

        if (_errors.size() == 0) {
            return true;
        }
        return false;
    }

    /*
     * Overload <code>dataIsValid</code> for default behavior (all checks)
     */
    public boolean dataIsValid() {
        return dataIsValid(false);
    }

    /*
     * List all errors on data
     * List all errors detected by <code>isValidData</code>.
     * @return Array of <code>String</true> containing error messages
     * If <code>isValidData</code> has never been called, array is empty
     */
    public String[] errors() {
        String[] all_errors = new String[_errors.size()];
        int i = 0;
        for(Enumeration en = _errors.elements(); en.hasMoreElements();) {
            all_errors[i] = (String) en.nextElement();
            i++;
        }
        return all_errors;
    }

    /*
     * Current error message
     * @return <code>String</true> containing the current error message
     * It's the first one in the list.
     */
    public String errorMessage() {
        return (String) _errors.firstElement();
    }
}
