package com.iiproject.donationpj.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getDateTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = formatter.format(date);
        return dateStr;
    }
}
