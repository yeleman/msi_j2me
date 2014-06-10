/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msisr;

import java.util.Date;
/**
 *
 * @author Fad
 */
public class SharedChecks {

    private static final String month_list[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public static int[] formatDateString(Date date_obj) {
        String date = date_obj.toString();
        int day = Integer.valueOf(date.substring(8, 10)).intValue();
        int month = monthFromString(date.substring(4,7));
        int start = 24;
        int end = date.length();
        if (end == 34){
            start = 30;
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

    public static boolean dateIsNotFuture(int month, int year) {
        // check sur les dates. En fin d'eliminer ceux qui sont au future

        Date now = new Date();
        int now_array[] = formatDateString(now);
        int now_month = now_array[1];
        int now_year = now_array[2];
        if (year > now_year) {
            return false;
        } else if (year == now_year) {
            if (month >= now_month){
                return false;
            }
          }
        return true;

    }

    public static boolean dateIsNotPast(int month, int year) {
        // check sur les dates. En fin d'eliminer ceux qui sont au passé
        Date now = new Date();
        int now_array[] = formatDateString(now);
        int now_month = now_array[1];
        int now_year = now_array[2];

        if (year < (now_year - 1)) {
            return false;
        } else if (year == (now_year - 1)) {
            if (month != 12) {
                return false;
            }
        } else if (month < (now_month - 1)){
            return false;
        }
        return true;
    }
}


