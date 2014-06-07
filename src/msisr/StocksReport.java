
package msisr;

import javax.microedition.rms.*;
import java.io.*;
import java.util.*;

/**
 * Stores data associated with Stocks report.
 * Includes validation logic and internal storage/reload.
 * Data is stored in RMS in a dedicated DB of exaclty 1 row.
 * All data is public but use of <code>setAll</code> method
 * is encouraged.
 * @author rgaudin
 */
public class StocksReport implements ReportPartInterface {

    private String database = "stocks";
    private RecordStore recordstore = null;
    private Vector _errors = new Vector();

    public int intrauterine_devices_initial = -1;
    public int intrauterine_devices_used = -1;
    public int intrauterine_devices_lost = -1;
    public int intrauterine_devices_received = -1;
    public int implants_initial = -1;
    public int implants_used = -1;
    public int implants_lost = -1;
    public int implants_received = -1;
    public int injections_initial = -1;
    public int injections_used = -1;
    public int injections_lost = -1;
    public int injections_received = -1;
    public int pills_initial = -1;
    public int pills_used = -1;
    public int pills_lost = -1;
    public int pills_received = -1;
    public int male_condoms_initial = -1;
    public int male_condoms_used = -1;
    public int male_condoms_lost = -1;
    public int male_condoms_received = -1;
    public int female_condoms_initial = -1;
    public int female_condoms_used = -1;
    public int female_condoms_lost = -1;
    public int female_condoms_received = -1;
    public int hiv_tests_initial = -1;
    public int hiv_tests_used = -1;
    public int hiv_tests_lost = -1;
    public int hiv_tests_received = -1;
    public int pregnancy_tests_initial = -1;
    public int pregnancy_tests_used = -1;
    public int pregnancy_tests_lost = -1;
    public int pregnancy_tests_received = -1;

    public String intrauterine_devices_observation = "-";
    public String implants_observation = "-";
    public String injections_observation = "-";
    public String pills_observation = "-";
    public String male_condoms_observation = "-";
    public String female_condoms_observation = "-";
    public String hiv_tests_observation = "-";
    public String pregnancy_tests_observation = "-";

    public StocksReport() {
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

        intrauterine_devices_initial = inputDataStream.readInt();
        intrauterine_devices_used = inputDataStream.readInt();
        intrauterine_devices_lost = inputDataStream.readInt();
        intrauterine_devices_received = inputDataStream.readInt();
        implants_initial = inputDataStream.readInt();
        implants_used = inputDataStream.readInt();
        implants_lost = inputDataStream.readInt();
        implants_received = inputDataStream.readInt();
        injections_initial = inputDataStream.readInt();
        injections_used = inputDataStream.readInt();
        injections_lost = inputDataStream.readInt();
        injections_received = inputDataStream.readInt();
        pills_initial = inputDataStream.readInt();
        pills_used = inputDataStream.readInt();
        pills_lost = inputDataStream.readInt();
        pills_received = inputDataStream.readInt();
        male_condoms_initial = inputDataStream.readInt();
        male_condoms_used = inputDataStream.readInt();
        male_condoms_lost = inputDataStream.readInt();
        male_condoms_received = inputDataStream.readInt();
        female_condoms_initial = inputDataStream.readInt();
        female_condoms_used = inputDataStream.readInt();
        female_condoms_lost = inputDataStream.readInt();
        female_condoms_received = inputDataStream.readInt();
        hiv_tests_initial = inputDataStream.readInt();
        hiv_tests_used = inputDataStream.readInt();
        hiv_tests_lost = inputDataStream.readInt();
        hiv_tests_received = inputDataStream.readInt();
        pregnancy_tests_initial = inputDataStream.readInt();
        pregnancy_tests_used = inputDataStream.readInt();
        pregnancy_tests_lost = inputDataStream.readInt();
        pregnancy_tests_received = inputDataStream.readInt();

        intrauterine_devices_observation = inputDataStream.readUTF();
        implants_observation = inputDataStream.readUTF();
        injections_observation = inputDataStream.readUTF();
        pills_observation = inputDataStream.readUTF();
        male_condoms_observation = inputDataStream.readUTF();
        female_condoms_observation = inputDataStream.readUTF();
        hiv_tests_observation = inputDataStream.readUTF();
        pregnancy_tests_observation = inputDataStream.readUTF();

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
        outputDataStream.writeInt(intrauterine_devices_initial);
        outputDataStream.writeInt(intrauterine_devices_used);
        outputDataStream.writeInt(intrauterine_devices_lost);
        outputDataStream.writeInt(intrauterine_devices_received);
        outputDataStream.writeInt(implants_initial);
        outputDataStream.writeInt(implants_used);
        outputDataStream.writeInt(implants_lost);
        outputDataStream.writeInt(implants_received);
        outputDataStream.writeInt(injections_initial);
        outputDataStream.writeInt(injections_used);
        outputDataStream.writeInt(injections_lost);
        outputDataStream.writeInt(injections_received);
        outputDataStream.writeInt(pills_initial);
        outputDataStream.writeInt(pills_used);
        outputDataStream.writeInt(pills_lost);
        outputDataStream.writeInt(pills_received);
        outputDataStream.writeInt(male_condoms_initial);
        outputDataStream.writeInt(male_condoms_used);
        outputDataStream.writeInt(male_condoms_lost);
        outputDataStream.writeInt(male_condoms_received);
        outputDataStream.writeInt(female_condoms_initial);
        outputDataStream.writeInt(female_condoms_used);
        outputDataStream.writeInt(female_condoms_lost);
        outputDataStream.writeInt(female_condoms_received);
        outputDataStream.writeInt(hiv_tests_initial);
        outputDataStream.writeInt(hiv_tests_used);
        outputDataStream.writeInt(hiv_tests_lost);
        outputDataStream.writeInt(hiv_tests_received);
        outputDataStream.writeInt(pregnancy_tests_initial);
        outputDataStream.writeInt(pregnancy_tests_used);
        outputDataStream.writeInt(pregnancy_tests_lost);
        outputDataStream.writeInt(pregnancy_tests_received);

        outputDataStream.writeUTF(intrauterine_devices_observation);
        outputDataStream.writeUTF(implants_observation);
        outputDataStream.writeUTF(injections_observation);
        outputDataStream.writeUTF(pills_observation);
        outputDataStream.writeUTF(male_condoms_observation);
        outputDataStream.writeUTF(female_condoms_observation);
        outputDataStream.writeUTF(hiv_tests_observation);
        outputDataStream.writeUTF(pregnancy_tests_observation);

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

    public String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ( (e = str.indexOf( pattern, s ) ) >= 0 )
        {
            result.append(str.substring( s, e ) );
            result.append( replace );
            s = e+pattern.length();
        }
        result.append( str.substring( s ) );
        return result.toString();
    }

    public String encodeStringForSMS(String text) {
        return this.replace(text, " ", "#");
    }

    /*
     * generates a string to be used in SMS
     * @return long <code>String</true> describing all data
     * formatted for SMS sending.
     */
    public String toSMSFormat() {
        String sep = " ";
        return intrauterine_devices_initial + sep +
               intrauterine_devices_used + sep +
               intrauterine_devices_lost + sep +
               intrauterine_devices_received + sep +
               implants_initial + sep +
               implants_used + sep +
               implants_lost + sep +
               implants_received + sep +
               injections_initial + sep +
               injections_used + sep +
               injections_lost + sep +
               injections_received + sep +
               pills_initial + sep +
               pills_used + sep +
               pills_lost + sep +
               pills_received + sep +
               male_condoms_initial + sep +
               male_condoms_used + sep +
               male_condoms_lost + sep +
               male_condoms_received + sep +
               female_condoms_initial + sep +
               female_condoms_used + sep +
               female_condoms_lost + sep +
               female_condoms_received + sep +
               hiv_tests_initial + sep +
               hiv_tests_used + sep +
               hiv_tests_lost + sep +
               hiv_tests_received + sep +
               pregnancy_tests_initial + sep +
               pregnancy_tests_used + sep +
               pregnancy_tests_lost + sep +
               pregnancy_tests_received + sep +
               this.encodeStringForSMS(intrauterine_devices_observation) + sep +
               this.encodeStringForSMS(implants_observation) + sep +
               this.encodeStringForSMS(injections_observation) + sep +
               this.encodeStringForSMS(pills_observation) + sep +
               this.encodeStringForSMS(male_condoms_observation) + sep +
               this.encodeStringForSMS(female_condoms_observation) + sep +
               this.encodeStringForSMS(hiv_tests_observation) + sep +
               this.encodeStringForSMS(pregnancy_tests_observation);
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
        int intrauterine_devices_consumed = intrauterine_devices_used + intrauterine_devices_lost;
        int intrauterine_devices_available = intrauterine_devices_initial + intrauterine_devices_received;
        if (intrauterine_devices_consumed > intrauterine_devices_available) {
            _errors.addElement("DIU: Utilisé+perdu ("+ intrauterine_devices_consumed +") supérieur à Initial+Reçue ("+ intrauterine_devices_available +").");
        }

        int implants_consumed = implants_used + implants_lost;
        int implants_available = implants_initial + implants_received;
        if (implants_consumed > implants_available) {
            _errors.addElement("Implant: Utilisé+perdu ("+ implants_consumed +") supérieur à Initial+Reçue ("+ implants_available +").");
        }

        int injections_consumed = injections_used + injections_lost;
        int injections_available = injections_initial + injections_received;
        if (injections_consumed > injections_available) {
            _errors.addElement("Injectable: Utilisé+perdu ("+ injections_consumed +") supérieur à Initial+Reçue ("+ injections_available +").");
        }

        int pills_consumed = pills_used + pills_lost;
        int pills_available = pills_initial + pills_received;
        if (pills_consumed > pills_available) {
            _errors.addElement("Pillule: Utilisé+perdu ("+ pills_consumed +") supérieur à Initial+Reçue ("+ pills_available +").");
        }

        int male_condoms_consumed = male_condoms_used + male_condoms_lost;
        int male_condoms_available = male_condoms_initial + male_condoms_received;
        if (male_condoms_consumed > male_condoms_available) {
            _errors.addElement("Préservatif masc.: Utilisé+perdu ("+ male_condoms_consumed +") supérieur à Initial+Reçue ("+ male_condoms_available +").");
        }

        int female_condoms_consumed = female_condoms_used + female_condoms_lost;
        int female_condoms_available = female_condoms_initial + female_condoms_received;
        if (female_condoms_consumed > female_condoms_available) {
            _errors.addElement("Préservatif femin.: Utilisé+perdu ("+ female_condoms_consumed +") supérieur à Initial+Reçue ("+ female_condoms_available +").");
        }

        int hiv_tests_consumed = hiv_tests_used + hiv_tests_lost;
        int hiv_tests_available = hiv_tests_initial + hiv_tests_received;
        if (hiv_tests_consumed > hiv_tests_available) {
            _errors.addElement("Test VIH: Utilisé+perdu ("+ hiv_tests_consumed +") supérieur à Initial+Reçue ("+ hiv_tests_available +").");
        }

        int pregnancy_tests_consumed = pregnancy_tests_used + pregnancy_tests_lost;
        int pregnancy_tests_available = pregnancy_tests_initial + pregnancy_tests_received;
        if (pregnancy_tests_consumed > pregnancy_tests_available) {
            _errors.addElement("Clearview: Utilisé+perdu ("+ pregnancy_tests_consumed +") supérieur à Initial+Reçue ("+ pregnancy_tests_available +").");
        }

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
     * @param intrauterine_devices_initial
     * @param intrauterine_devices_used
     * @param intrauterine_devices_lost
     * @param intrauterine_devices_received
     * @param implants_initial
     * @param implants_used
     * @param implants_lost
     * @param implants_received
     * @param injections_initial
     * @param injections_used
     * @param injections_lost
     * @param injections_received
     * @param pills_initial
     * @param pills_used
     * @param pills_lost
     * @param pills_received
     * @param male_condoms_initial
     * @param male_condoms_used
     * @param male_condoms_lost
     * @param male_condoms_received
     * @param female_condoms_initial
     * @param female_condoms_used
     * @param female_condoms_lost
     * @param female_condoms_received
     * @param hiv_tests_initial
     * @param hiv_tests_used
     * @param hiv_tests_lost
     * @param hiv_tests_received
     * @param pregnancy_tests_initial
     * @param pregnancy_tests_used
     * @param pregnancy_tests_lost
     * @param pregnancy_tests_received
     * @param intrauterine_devices_observation
     * @param implants_observation
     * @param injections_observation
     * @param pills_observation
     * @param male_condoms_observation
     * @param female_condoms_observation
     * @param hiv_tests_observation
     * @param pregnancy_tests_observation
     */
    public void setAll(int intrauterine_devices_initial,
                       int intrauterine_devices_used,
                       int intrauterine_devices_lost,
                       int intrauterine_devices_received,
                       int implants_initial,
                       int implants_used,
                       int implants_lost,
                       int implants_received,
                       int injections_initial,
                       int injections_used,
                       int injections_lost,
                       int injections_received,
                       int pills_initial,
                       int pills_used,
                       int pills_lost,
                       int pills_received,
                       int male_condoms_initial,
                       int male_condoms_used,
                       int male_condoms_lost,
                       int male_condoms_received,
                       int female_condoms_initial,
                       int female_condoms_used,
                       int female_condoms_lost,
                       int female_condoms_received,
                       int hiv_tests_initial,
                       int hiv_tests_used,
                       int hiv_tests_lost,
                       int hiv_tests_received,
                       int pregnancy_tests_initial,
                       int pregnancy_tests_used,
                       int pregnancy_tests_lost,
                       int pregnancy_tests_received,
                       String intrauterine_devices_observation,
                       String implants_observation,
                       String injections_observation,
                       String pills_observation,
                       String male_condoms_observation,
                       String female_condoms_observation,
                       String hiv_tests_observation,
                       String pregnancy_tests_observation) {

        this.intrauterine_devices_initial = intrauterine_devices_initial;
        this.intrauterine_devices_used = intrauterine_devices_used;
        this.intrauterine_devices_lost = intrauterine_devices_lost;
        this.intrauterine_devices_received = intrauterine_devices_received;
        this.implants_initial = implants_initial;
        this.implants_used = implants_used;
        this.implants_lost = implants_lost;
        this.implants_received = implants_received;
        this.injections_initial = injections_initial;
        this.injections_used = injections_used;
        this.injections_lost = injections_lost;
        this.injections_received = injections_received;
        this.pills_initial = pills_initial;
        this.pills_used = pills_used;
        this.pills_lost = pills_lost;
        this.pills_received = pills_received;
        this.male_condoms_initial = male_condoms_initial;
        this.male_condoms_used = male_condoms_used;
        this.male_condoms_lost = male_condoms_lost;
        this.male_condoms_received = male_condoms_received;
        this.female_condoms_initial = female_condoms_initial;
        this.female_condoms_used = female_condoms_used;
        this.female_condoms_lost = female_condoms_lost;
        this.female_condoms_received = female_condoms_received;
        this.hiv_tests_initial = hiv_tests_initial;
        this.hiv_tests_used = hiv_tests_used;
        this.hiv_tests_lost = hiv_tests_lost;
        this.hiv_tests_received = hiv_tests_received;
        this.pregnancy_tests_initial = pregnancy_tests_initial;
        this.pregnancy_tests_used = pregnancy_tests_used;
        this.pregnancy_tests_lost = pregnancy_tests_lost;
        this.pregnancy_tests_received = pregnancy_tests_received;
        this.intrauterine_devices_observation = intrauterine_devices_observation;
        this.implants_observation = implants_observation;
        this.injections_observation = injections_observation;
        this.pills_observation = pills_observation;
        this.male_condoms_observation = male_condoms_observation;
        this.female_condoms_observation = female_condoms_observation;
        this.hiv_tests_observation = hiv_tests_observation;
        this.pregnancy_tests_observation = pregnancy_tests_observation;
    }

    public boolean dataIsComplete() {
        // all *_observation fields are optional.
        if (this.intrauterine_devices_initial != -1 &&
            this.intrauterine_devices_used != -1 &&
            this.intrauterine_devices_lost != -1 &&
            this.intrauterine_devices_received != -1 &&
            this.implants_initial != -1 &&
            this.implants_used != -1 &&
            this.implants_lost != -1 &&
            this.implants_received != -1 &&
            this.injections_initial != -1 &&
            this.injections_used != -1 &&
            this.injections_lost != -1 &&
            this.injections_received != -1 &&
            this.pills_initial != -1 &&
            this.pills_used != -1 &&
            this.pills_lost != -1 &&
            this.pills_received != -1 &&
            this.male_condoms_initial != -1 &&
            this.male_condoms_used != -1 &&
            this.male_condoms_lost != -1 &&
            this.male_condoms_received != -1 &&
            this.female_condoms_initial != -1 &&
            this.female_condoms_used != -1 &&
            this.female_condoms_lost != -1 &&
            this.female_condoms_received != -1 &&
            this.hiv_tests_initial != -1 &&
            this.hiv_tests_used != -1 &&
            this.hiv_tests_lost != -1 &&
            this.hiv_tests_received != -1 &&
            this.pregnancy_tests_initial != -1 &&
            this.pregnancy_tests_used != -1 &&
            this.pregnancy_tests_lost != -1 &&
            this.pregnancy_tests_received != -1) {
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
