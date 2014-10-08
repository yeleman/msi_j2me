
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
public class FinancialReport implements ReportPartInterface {

    private String database = "financial";
    private RecordStore recordstore = null;
    private Vector _errors = new Vector();

    public int intrauterine_devices_qty = -1;
    public int intrauterine_devices_price = -1;
    public int intrauterine_devices_revenue = -1;
    public int implants_qty = -1;
    public int implants_price = -1;
    public int implants_revenue = -1;
    public int injections_qty = -1;
    public int injections_price = -1;
    public int injections_revenue = -1;
    public int pills_qty = -1;
    public int pills_price = -1;
    public int pills_revenue = -1;
    public int male_condoms_qty = -1;
    public int male_condoms_price = -1;
    public int male_condoms_revenue = -1;
    public int female_condoms_qty = -1;
    public int female_condoms_price = -1;
    public int female_condoms_revenue = -1;
    public int hiv_tests_qty = -1;
    public int hiv_tests_price = -1;
    public int hiv_tests_revenue = -1;
    public int iud_removal_qty = -1;
    public int iud_removal_price = -1;
    public int iud_removal_revenue = -1;
    public int implant_removal_qty = -1;
    public int implant_removal_price = -1;
    public int implant_removal_revenue = -1;

    public int emergency_controls_qty = -1;
    public int emergency_controls_price = -1;
    public int emergency_controls_revenue = -1;
    
    public FinancialReport() {
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

        intrauterine_devices_qty = inputDataStream.readInt();
        intrauterine_devices_price = inputDataStream.readInt();
        intrauterine_devices_revenue = inputDataStream.readInt();
        implants_qty = inputDataStream.readInt();
        implants_price = inputDataStream.readInt();
        implants_revenue = inputDataStream.readInt();
        injections_qty = inputDataStream.readInt();
        injections_price = inputDataStream.readInt();
        injections_revenue = inputDataStream.readInt();
        pills_qty = inputDataStream.readInt();
        pills_price = inputDataStream.readInt();
        pills_revenue = inputDataStream.readInt();
        male_condoms_qty = inputDataStream.readInt();
        male_condoms_price = inputDataStream.readInt();
        male_condoms_revenue = inputDataStream.readInt();
        female_condoms_qty = inputDataStream.readInt();
        female_condoms_price = inputDataStream.readInt();
        female_condoms_revenue = inputDataStream.readInt();
        hiv_tests_qty = inputDataStream.readInt();
        hiv_tests_price = inputDataStream.readInt();
        hiv_tests_revenue = inputDataStream.readInt();
        iud_removal_qty = inputDataStream.readInt();
        iud_removal_price = inputDataStream.readInt();
        iud_removal_revenue = inputDataStream.readInt();
        implant_removal_qty = inputDataStream.readInt();
        implant_removal_price = inputDataStream.readInt();
        implant_removal_revenue = inputDataStream.readInt();
        emergency_controls_qty = inputDataStream.readInt();
        emergency_controls_price = inputDataStream.readInt();
        emergency_controls_revenue = inputDataStream.readInt();

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
        outputDataStream.writeInt(intrauterine_devices_qty);
        outputDataStream.writeInt(intrauterine_devices_price);
        outputDataStream.writeInt(intrauterine_devices_revenue);
        outputDataStream.writeInt(implants_qty);
        outputDataStream.writeInt(implants_price);
        outputDataStream.writeInt(implants_revenue);
        outputDataStream.writeInt(injections_qty);
        outputDataStream.writeInt(injections_price);
        outputDataStream.writeInt(injections_revenue);
        outputDataStream.writeInt(pills_qty);
        outputDataStream.writeInt(pills_price);
        outputDataStream.writeInt(pills_revenue);
        outputDataStream.writeInt(male_condoms_qty);
        outputDataStream.writeInt(male_condoms_price);
        outputDataStream.writeInt(male_condoms_revenue);
        outputDataStream.writeInt(female_condoms_qty);
        outputDataStream.writeInt(female_condoms_price);
        outputDataStream.writeInt(female_condoms_revenue);
        outputDataStream.writeInt(hiv_tests_qty);
        outputDataStream.writeInt(hiv_tests_price);
        outputDataStream.writeInt(hiv_tests_revenue);
        outputDataStream.writeInt(iud_removal_qty);
        outputDataStream.writeInt(iud_removal_price);
        outputDataStream.writeInt(iud_removal_revenue);
        outputDataStream.writeInt(implant_removal_qty);
        outputDataStream.writeInt(implant_removal_price);
        outputDataStream.writeInt(implant_removal_revenue);
        outputDataStream.writeInt(emergency_controls_qty);
        outputDataStream.writeInt(emergency_controls_price);
        outputDataStream.writeInt(emergency_controls_revenue);

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
        return intrauterine_devices_qty + sep +
               intrauterine_devices_price + sep +
               intrauterine_devices_revenue + sep +
               implants_qty + sep +
               implants_price + sep +
               implants_revenue + sep +
               injections_qty + sep +
               injections_price + sep +
               injections_revenue + sep +
               pills_qty + sep +
               pills_price + sep +
               pills_revenue + sep +
               male_condoms_qty + sep +
               male_condoms_price + sep +
               male_condoms_revenue + sep +
               female_condoms_qty + sep +
               female_condoms_price + sep +
               female_condoms_revenue + sep +
               hiv_tests_qty + sep +
               hiv_tests_price + sep +
               hiv_tests_revenue + sep +
               iud_removal_qty + sep +
               iud_removal_price + sep +
               iud_removal_revenue + sep +
               implant_removal_qty + sep +
               implant_removal_price + sep +
               implant_removal_revenue + sep +
               emergency_controls_qty + sep +
               emergency_controls_price + sep +
               emergency_controls_revenue;
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
        if ((intrauterine_devices_qty * intrauterine_devices_price) < intrauterine_devices_revenue) {
            _errors.addElement("DIU: montant inférieur aux revenus net.");
        }

        if ((implants_qty * implants_price) < implants_revenue) {
            _errors.addElement("Implants: montant inférieur aux revenus net.");
        }

        if ((injections_qty * injections_price) < injections_revenue) {
            _errors.addElement("Injectable: montant inférieur aux revenus net.");
        }

        if ((pills_qty * pills_price) < pills_revenue) {
            _errors.addElement("Pillule: montant inférieur aux revenus net.");
        }

        if ((male_condoms_qty * male_condoms_price) < male_condoms_revenue) {
            _errors.addElement("Préservatif masc.: montant inférieur aux revenus net.");
        }

        if ((female_condoms_qty * female_condoms_price) < female_condoms_revenue) {
            _errors.addElement("Préservatif femin.: montant inférieur aux revenus net.");
        }

        if ((hiv_tests_qty * hiv_tests_price) < hiv_tests_revenue) {
            _errors.addElement("Test VIH: montant inférieur aux revenus net.");
        }

        if ((iud_removal_qty * iud_removal_price) < iud_removal_revenue) {
            _errors.addElement("Retrait DIU: montant inférieur aux revenus net.");
        }

        if ((implant_removal_qty * implant_removal_price) < implant_removal_revenue) {
            _errors.addElement("Retrait Implant: montant inférieur aux revenus net.");
        }
        if ((emergency_controls_qty * emergency_controls_price) < emergency_controls_revenue) {
            _errors.addElement("Contraception d'urgence: montant inférieur aux revenue net.");
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
     * @param intrauterine_devices_qty
     * @param intrauterine_devices_price
     * @param intrauterine_devices_revenue
     * @param implants_qty
     * @param implants_price
     * @param implants_revenue
     * @param injections_qty
     * @param injections_price
     * @param injections_revenue
     * @param pills_qty
     * @param pills_price
     * @param pills_revenue
     * @param male_condoms_qty
     * @param male_condoms_price
     * @param male_condoms_revenue
     * @param female_condoms_qty
     * @param female_condoms_price
     * @param female_condoms_revenue
     * @param hiv_tests_qty
     * @param hiv_tests_price
     * @param hiv_tests_revenue
     * @param iud_removal_qty
     * @param iud_removal_price
     * @param iud_removal_revenue
     * @param implant_removal_qty
     * @param implant_removal_price
     * @param implant_removal_revenue
     */
    public void setAll(int intrauterine_devices_qty,
                       int intrauterine_devices_price,
                       int intrauterine_devices_revenue,
                       int implants_qty,
                       int implants_price,
                       int implants_revenue,
                       int injections_qty,
                       int injections_price,
                       int injections_revenue,
                       int pills_qty,
                       int pills_price,
                       int pills_revenue,
                       int male_condoms_qty,
                       int male_condoms_price,
                       int male_condoms_revenue,
                       int female_condoms_qty,
                       int female_condoms_price,
                       int female_condoms_revenue,
                       int hiv_tests_qty,
                       int hiv_tests_price,
                       int hiv_tests_revenue,
                       int iud_removal_qty,
                       int iud_removal_price,
                       int iud_removal_revenue,
                       int implant_removal_qty,
                       int implant_removal_price,
                       int implant_removal_revenue,
                       int emergency_controls_qty,
                       int emergency_controls_price,
                       int emergency_controls_revenue) {
        this.intrauterine_devices_price = intrauterine_devices_price;
        this.intrauterine_devices_qty = intrauterine_devices_qty;
        this.intrauterine_devices_price = intrauterine_devices_price;
        this.intrauterine_devices_revenue = intrauterine_devices_revenue;
        this.implants_price = implants_price;
        this.implants_qty = implants_qty;
        this.implants_price = implants_price;
        this.implants_revenue = implants_revenue;
        this.injections_price = injections_price;
        this.injections_qty = injections_qty;
        this.injections_price = injections_price;
        this.injections_revenue = injections_revenue;
        this.pills_price = pills_price;
        this.pills_qty = pills_qty;
        this.pills_price = pills_price;
        this.pills_revenue = pills_revenue;
        this.male_condoms_price = male_condoms_price;
        this.male_condoms_qty = male_condoms_qty;
        this.male_condoms_price = male_condoms_price;
        this.male_condoms_revenue = male_condoms_revenue;
        this.female_condoms_price = female_condoms_price;
        this.female_condoms_qty = female_condoms_qty;
        this.female_condoms_price = female_condoms_price;
        this.female_condoms_revenue = female_condoms_revenue;
        this.hiv_tests_price = hiv_tests_price;
        this.hiv_tests_qty = hiv_tests_qty;
        this.hiv_tests_price = hiv_tests_price;
        this.hiv_tests_revenue = hiv_tests_revenue;
        this.iud_removal_price = iud_removal_price;
        this.iud_removal_qty = iud_removal_qty;
        this.iud_removal_price = iud_removal_price;
        this.iud_removal_revenue = iud_removal_revenue;
        this.implant_removal_price = implant_removal_price;
        this.implant_removal_qty = implant_removal_qty;
        this.implant_removal_price = implant_removal_price;
        this.implant_removal_revenue = implant_removal_revenue;
        this.emergency_controls_qty = emergency_controls_qty;
        this.emergency_controls_price  = emergency_controls_price;
        this.emergency_controls_revenue = emergency_controls_revenue;
    }

    public boolean dataIsComplete() {
        if (this.intrauterine_devices_qty != -1 &&
            this.intrauterine_devices_price != -1 &&
            this.intrauterine_devices_revenue != -1 &&
            this.implants_qty != -1 &&
            this.implants_price != -1 &&
            this.implants_revenue != -1 &&
            this.injections_qty != -1 &&
            this.injections_price != -1 &&
            this.injections_revenue != -1 &&
            this.pills_qty != -1 &&
            this.pills_price != -1 &&
            this.pills_revenue != -1 &&
            this.male_condoms_qty != -1 &&
            this.male_condoms_price != -1 &&
            this.male_condoms_revenue != -1 &&
            this.female_condoms_qty != -1 &&
            this.female_condoms_price != -1 &&
            this.female_condoms_revenue != -1 &&
            this.hiv_tests_qty != -1 &&
            this.hiv_tests_price != -1 &&
            this.hiv_tests_revenue != -1 &&
            this.iud_removal_qty != -1 &&
            this.iud_removal_price != -1 &&
            this.iud_removal_revenue != -1 &&
            this.implant_removal_qty != -1 &&
            this.implant_removal_price != -1 &&
            this.implant_removal_revenue != -1 &&
            this.emergency_controls_qty != -1 &&
            this.emergency_controls_price != -1 &&
            this.emergency_controls_revenue != -1) {
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
