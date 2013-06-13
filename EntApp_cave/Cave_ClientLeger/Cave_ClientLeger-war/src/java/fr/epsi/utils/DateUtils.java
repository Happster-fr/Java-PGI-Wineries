/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikhael
 */
public class DateUtils {
    public static final String FORMAT_DDMMYYYY = "MM/dd/yyyy";
    
    public static Date stringToDate(String sDate, String sFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        try {
            return sdf.parse(sDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
