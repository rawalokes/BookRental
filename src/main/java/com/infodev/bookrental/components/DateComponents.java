package com.infodev.bookrental.components;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:4:54 PM
 */
public class DateComponents {
    public static String currentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd ");
        Date date = new Date();
         return formatter.format(date);


    }


}
