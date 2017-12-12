package com.example.qs.Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by phoebegl on 2017/12/11.
 */
public class DateUtil {

    public static String transferDate(String date){
        date = date.replace("Z", "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");//注意格式化的表达式
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    public static String calEndTime(String start, int last) {
        start = transferDate(start);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date current = format.parse(start);
            current.setTime(current.getTime() + last*60*1000);
            return format.format(current);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
