/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msi_sr;

import java.util.Date;
import java.util.Hashtable;
import java.util.Enumeration;

import msi_sr.Configuration.*;

/**
 * @author Fad
 */
public class SharedChecks {
    private static final String month_list[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public static int[] formatDateString(Date date_obj) {
        String date = date_obj.toString();
        int day = Integer.valueOf(date.substring(8, 10)).intValue();
        int month = monthFromString(date.substring(4,7));
        int start = 24;
        int end = 28;
        if (date.length()==34){
            start = 30;
            end = 34;
        }
        int year = Integer.valueOf(date.substring(start, end)).intValue();
        int list_date[] = {day, month, year};
        return list_date;
    }

    public static int monthFromString(String month_str) {
        int i;
        for(i=0; i<=month_list.length; i++){
            if(month_list[i].equals(month_str)){
                return i + 1;
            }
        }
        return 1;
    }

    public static boolean isDateValide(Date date_obj) {
        // check sur les dates. En fin d'eliminer ceux qui sont au future
        int array[] = formatDateString(date_obj);
        int day = array[0];
        int month = array[1];
        int year = array[2];

        Date now = new Date();
        int now_array[] = formatDateString(now);
        int now_day = now_array[0];
        int now_month = now_array[1];
        int now_year = now_array[2];

        if (year < now_year) {
            return true;
        }
        else if (year == now_year) {
            if (month < now_month){
                return true;
            }
            else if (month == now_month){
                if (day <= now_day){
                    return true;
                }
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private static int[] previous_date( int day, int month, int year) {
        int new_month[] = {day, month, year};
        if (month > 1){
            new_month[1] = month -1;
            new_month[2] = year;
        }
        else{
            new_month[1] = 12;
            new_month[2] = year - 1;
        }
        return new_month;
    }

    private static boolean is_before(int day1, int month1, int year1, int day2, int month2, int year2) {

        if (year1 < year2) {
            return true;
        }
        else if (year1 == year2) {

            if (month1 < month2){
                return true;
            }
            else if (month1 == month2){
                if (day1 < day2){
                    return true;
                }
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static boolean compareleftarrived(Date arrived_on, Date left_on) {
        // all fields are required to be filled.
        int array[] = formatDateString(arrived_on);
        int arrived_on_day = array[0];
        int arrived_on_month = array[1];
        int arrived_on_year = array[2];

        int[] dateU5 = formatDateString(left_on);
        int left_on_day = dateU5[0];
        int left_on_month = dateU5[1];
        int left_on_year = dateU5[2];

        if (is_before(left_on_day, left_on_month, left_on_year, arrived_on_day,
                      arrived_on_month, arrived_on_year) == true)
            return true;
        else
            return false;
    }

    public static String addzero(int num){
        String snum = "";
        if (num < 10)
            snum = "0" + num;
        else
            snum = snum + num;
        return snum;
    }

    public static String[] getKeys(Hashtable ht) {
        int num_keys = ht.size();
        String[] keys = new String[num_keys];
        int i = 0;

        for (Enumeration e = ht.keys() ; e.hasMoreElements() ;) {
            keys[i] = (String)e.nextElement();
            i++;
        }
        try {
            bubbleSort(keys);
        } catch (Exception err) {
            System.out.println("Failed to sort keys.");
        }
        return keys;
    }

    static void bubbleSort(String[] p_array) throws Exception {
        boolean anyCellSorted;
        int length = p_array.length;
        String tmp;
        for (int i = length; --i >= 0;) {
            anyCellSorted = false;
            for (int j = 0; j < i; j++) {
                if (p_array[j].compareTo(p_array[j + 1]) > 0) {
                    tmp = p_array[j];
                    p_array[j] = p_array[j + 1];
                    p_array[j + 1] = tmp;
                    anyCellSorted = true;
                }

            }
            if (anyCellSorted == false) {
                return;
            }
        }
    }

}
