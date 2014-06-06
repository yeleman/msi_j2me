
package msisr;

import javax.microedition.rms.*;
import java.io.*;
import java.util.*;

/**
 * Stores data associated with Provided Services report.
 * Includes validation logic and internal storage/reload.
 * Data is stored in RMS in a dedicated DB of exaclty 1 row.
 * All data is public but use of <code>setAll</code> method
 * is encouraged.
 * @author rgaudin
 */
public class ProvidedServicesReport implements ReportPartInterface {

    private String database = "provided_services";
    private RecordStore recordstore = null;
    private Vector _errors = new Vector();

    public int tubal_ligations = -1;
    public int intrauterine_devices = -1;
    public int injections = -1;
    public int pills = -1;
    public int male_condoms = -1;
    public int female_condoms = -1;
    public int emergency_controls = -1;
    public int implants = -1;
    public int new_clients = -1;
    public int previous_clients = -1;
    public int under25_visits = -1;
    public int over25_visits = -1;
    public int very_first_visits = -1;
    public int short_term_method_visits = -1;
    public int long_term_method_visits = -1;
    public int hiv_counseling_clients = -1;
    public int hiv_tests = -1;
    public int hiv_positive_results = -1;
    public int implant_removals = -1;
    public int iud_removal = -1;

    public ProvidedServicesReport() {
        try {
            this.initDB();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * initialize the DB
     * creates an row in the DB if it doesn't exist.
     * @throws <code>RecordStoreException</code> RMS can't open DB.
     */
    private void initDB() throws RecordStoreException {
        recordstore = RecordStore.openRecordStore(this.database, true );
        RecordEnumeration recordEnumeration = recordstore.enumerateRecords(null, null, false);
        recordstore.closeRecordStore();
         if (recordEnumeration.numRecords() < 1) {
             this.saveInStore(true);
        }
    }

    /*
     * fills object with value from DB.
     * @return <code>true</true> if load was successful
     * <code>false</code> otherwise.
     */
    public boolean loadFromStore() {

      try
      {
        // open record store
        recordstore = RecordStore.openRecordStore(this.database, true );

        // record is internally a byte array
        byte[] byteInputData = new byte[1024];

        // we'll retrieve data in a stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteInputData);
        DataInputStream inputDataStream = new DataInputStream(inputStream);

        // actually retrieve data
        recordstore.getRecord(1, byteInputData, 0);

        tubal_ligations = inputDataStream.readInt();
        intrauterine_devices = inputDataStream.readInt();
        injections = inputDataStream.readInt();
        pills = inputDataStream.readInt();
        male_condoms = inputDataStream.readInt();
        female_condoms = inputDataStream.readInt();
        emergency_controls = inputDataStream.readInt();
        implants = inputDataStream.readInt();
        new_clients = inputDataStream.readInt();
        previous_clients = inputDataStream.readInt();
        under25_visits = inputDataStream.readInt();
        over25_visits = inputDataStream.readInt();
        very_first_visits = inputDataStream.readInt();
        short_term_method_visits = inputDataStream.readInt();
        long_term_method_visits = inputDataStream.readInt();
        hiv_counseling_clients = inputDataStream.readInt();
        hiv_tests = inputDataStream.readInt();
        hiv_positive_results = inputDataStream.readInt();
        implant_removals = inputDataStream.readInt();
        iud_removal = inputDataStream.readInt();

        // close stream
        inputStream.reset();
        inputStream.close();
        inputDataStream.close();

        // close connection
        recordstore.closeRecordStore();
      }
      catch ( Exception error)
      {
          return false;
      }
      return true;
    }

    /*
     * saves content into DB.
     * @param add whether to add a record or not (update)
     * @return <code>true</true> if save was successful
     * <code>false</code> otherwise.
     */
    public boolean saveInStore(boolean add) {

      try
      {
        // open record store
        recordstore = RecordStore.openRecordStore(this.database, true );

        // record is internaly a byte array
        byte[] outputRecord;

        // store all data in a stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream outputDataStream = new DataOutputStream(outputStream);

        // add all fields to the stream
        outputDataStream.writeInt(tubal_ligations);
        outputDataStream.writeInt(intrauterine_devices);
        outputDataStream.writeInt(injections);
        outputDataStream.writeInt(pills);
        outputDataStream.writeInt(male_condoms);
        outputDataStream.writeInt(female_condoms);
        outputDataStream.writeInt(emergency_controls);
        outputDataStream.writeInt(implants);
        outputDataStream.writeInt(new_clients);
        outputDataStream.writeInt(previous_clients);
        outputDataStream.writeInt(under25_visits);
        outputDataStream.writeInt(over25_visits);
        outputDataStream.writeInt(very_first_visits);
        outputDataStream.writeInt(short_term_method_visits);
        outputDataStream.writeInt(long_term_method_visits);
        outputDataStream.writeInt(hiv_counseling_clients);
        outputDataStream.writeInt(hiv_tests);
        outputDataStream.writeInt(hiv_positive_results);
        outputDataStream.writeInt(implant_removals);
        outputDataStream.writeInt(iud_removal);

        // finish preparing stream
        outputDataStream.flush();
        outputRecord = outputStream.toByteArray();

        // actual record storage
        if (add) {
            recordstore.addRecord(outputRecord, 0, outputRecord.length);
        } else {
            recordstore.setRecord(1, outputRecord, 0, outputRecord.length);
        }

        // close stream
        outputStream.reset();
        outputStream.close();
        outputDataStream.close();

        // close connection
        recordstore.closeRecordStore();
      }
      catch ( Exception error)
      {
          return false;
      }
      return true;
    }

    /*
     * Overrides <code>saveInStore()</code> without parameter
     * Almost every call will use this override. Uses <code>false</code>
     * @return the value of <code>saveInStore(false)</code>
     */
    public boolean saveInStore() {
        return this.saveInStore(false);
    }

    /*
     * generates a string to be used in SMS
     * @return long <code>String</true> describing all data
     * formatted for SMS sending.
     */
    public String toSMSFormat() {
        String sep = " ";
        return tubal_ligations + sep +
                intrauterine_devices + sep +
                injections + sep +
                pills + sep +
                male_condoms + sep +
                female_condoms + sep +
                emergency_controls + sep +
                implants + sep +
                new_clients + sep +
                previous_clients + sep +
                under25_visits + sep +
                over25_visits + sep +
                very_first_visits + sep +
                short_term_method_visits + sep +
                long_term_method_visits + sep +
                hiv_counseling_clients + sep +
                hiv_tests + sep +
                hiv_positive_results + sep +
                implant_removals + sep +
                iud_removal;
    }

    /*
     * checks whether data is legit
     * checks all logic algorithms and fills the <code>_errors</code> variable.
     * @return <code>true</true> if all data is valid
     * <code>false</code> otherwise.
     */
    public boolean dataIsValid() {

        _errors = new Vector();

        if (!(dataIsComplete())) {
            _errors.addElement("Tous les champs ne sont pas remplis");
        }

        // various tests checking whether provided number are legit.
       // if (total_malaria_cases > total_consultation) {
       //     _errors.addElement("Cas de Palu supérieur au total toutes causes");
       // }

        if (_errors.size() == 0) {
            return true;
        }
        return false;
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

    /*
     * set all variables at once.
     * @param tubal_ligations
     * @param intrauterine_devices
     * @param injections
     * @param pills
     * @param male_condoms
     * @param female_condoms
     * @param emergency_controls
     * @param implants
     * @param new_clients
     * @param previous_clients
     * @param under25_visits
     * @param over25_visits
     * @param very_first_visits
     * @param short_term_method_visits
     * @param long_term_method_visits
     * @param hiv_counseling_clients
     * @param hiv_tests
     * @param hiv_positive_results
     * @param implant_removals
     * @param iud_removal
     */
    public void setAll(int tubal_ligations,
                       int intrauterine_devices,
                       int injections,
                       int pills,
                       int male_condoms,
                       int female_condoms,
                       int emergency_controls,
                       int implants,
                       int new_clients,
                       int previous_clients,
                       int under25_visits,
                       int over25_visits,
                       int very_first_visits,
                       int short_term_method_visits,
                       int long_term_method_visits,
                       int hiv_counseling_clients,
                       int hiv_tests,
                       int hiv_positive_results,
                       int implant_removals,
                       int iud_removal) {

    this.tubal_ligations = tubal_ligations;
    this.intrauterine_devices = intrauterine_devices;
    this.injections = injections;
    this.pills = pills;
    this.male_condoms = male_condoms;
    this.female_condoms = female_condoms;
    this.emergency_controls = emergency_controls;
    this.implants = implants;
    this.new_clients = new_clients;
    this.previous_clients = previous_clients;
    this.under25_visits = under25_visits;
    this.over25_visits = over25_visits;
    this.very_first_visits = very_first_visits;
    this.short_term_method_visits = short_term_method_visits;
    this.long_term_method_visits = long_term_method_visits;
    this.hiv_counseling_clients = hiv_counseling_clients;
    this.hiv_tests = hiv_tests;
    this.hiv_positive_results = hiv_positive_results;
    this.implant_removals = implant_removals;
    this.iud_removal = iud_removal;
    }

    public boolean dataIsComplete() {
        if (this.tubal_ligations != -1 &&
            this.intrauterine_devices != -1 &&
            this.injections != -1 &&
            this.pills != -1 &&
            this.male_condoms != -1 &&
            this.female_condoms != -1 &&
            this.emergency_controls != -1 &&
            this.implants != -1 &&
            this.new_clients != -1 &&
            this.previous_clients != -1 &&
            this.under25_visits != -1 &&
            this.over25_visits != -1 &&
            this.very_first_visits != -1 &&
            this.short_term_method_visits != -1 &&
            this.long_term_method_visits != -1 &&
            this.hiv_counseling_clients != -1 &&
            this.hiv_tests != -1 &&
            this.hiv_positive_results != -1 &&
            this.implant_removals != -1 &&
            this.iud_removal != -1) {
                return true;
        }
        return false;
    }

    public boolean delete() {
        try {
            RecordStore.deleteRecordStore(this.database);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
