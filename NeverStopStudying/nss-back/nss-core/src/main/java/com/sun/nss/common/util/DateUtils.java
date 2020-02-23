package com.sun.nss.common.util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author zcm
 */
public class DateUtils {

    private static final String DATE_TIME = "YYYY-mm-dd HH:mm:ss";

    /**
     * 时间 +- 分钟
     * @param date
     * @param minutes
     * @return
     */
    public static long addDateMinutes(Date date, int minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minutes);
        return calendar.getTime().getTime();
    }

    /**
     * 日期格式化
     * @param data
     * @param pattern
     * @return
     */
    public static String format(Data data ,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(data);
    }


    public static String format(Data data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME);
        return simpleDateFormat.format(data);
    }}
