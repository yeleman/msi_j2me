
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
