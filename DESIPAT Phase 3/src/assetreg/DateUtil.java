/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetreg;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author sgtan_
 */
public class DateUtil {
   
    private static final DateFormat utilDateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    private static final DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static java.sql.Date utilDateToSqlDate(java.util.Date uDate) throws ParseException{
        return java.sql.Date.valueOf(sqlDateFormatter.format(uDate));
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date sDate) throws ParseException{
    return (java.util.Date)utilDateFormatter.parse(utilDateFormatter.format(sDate));
    }
    
}
