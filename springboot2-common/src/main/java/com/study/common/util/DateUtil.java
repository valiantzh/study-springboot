package com.study.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期操作工具类
 * @author valiantzh
 * @version 1.0
 */
public class DateUtil {

    /*
     * public static java.util.Calendar utcCal = null; static{ utcCal =
     * Calendar.getInstance();
     * 
     * // get the TimeZone for "America/Los_Angeles" TimeZone tz =
     * TimeZone.getTimeZone("UTC"); utcCal.setTimeZone(tz); }
     */
    public static java.sql.Date minDate = null;

    static {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1900);
        c.set(Calendar.DATE, 1);
        // c.set(Calendar.MONTH, 1);
        c.set(Calendar.MONTH, 0);

        minDate = new java.sql.Date(c.getTime().getTime());
    }

    private DateUtil() {
    }

    public static String nowDate() {
        return nowDate("-");
    }

    public static String nowDate(String sper) {
        GregorianCalendar Time = new GregorianCalendar();

        String s_nowD = "", s_nowM = "";
        if (sper == null) {
            sper = "-";
        } else {
            sper = sper.trim();
        }

        int nowY = Time.get(Calendar.YEAR);
        int nowM = Time.get(Calendar.MONTH) + 1;

        if (nowM < 10) {
            s_nowM = "0" + nowM;
        } else {
            s_nowM = "" + nowM;
        }

        int nowD = Time.get(Calendar.DATE);
        if (nowD < 10) {
            s_nowD = "0" + nowD;
        } else {
            s_nowD = "" + nowD;
        }

        String result = nowY + sper + s_nowM + sper + s_nowD;

        return result;
    }

    public static String nowTime() {
        return nowTime(":");
    }

    public static String nowTime(String sperate) {
        GregorianCalendar Time = new GregorianCalendar();

        int i_hour = Time.get(Calendar.HOUR); // Hours() ;
        int i_tag = Time.get(Calendar.AM_PM);
        if (i_tag == 1 && i_hour < 12)
            i_hour = i_hour + 12;

        int i_minutes = Time.get(Calendar.MINUTE); // Minutes() ;
        int i_seconds = Time.get(Calendar.SECOND); // Seconds() ;
        int i_mill = Time.get(Calendar.MILLISECOND);

        String s_time = "";
        if (i_hour >= 0 && i_hour < 10)
            s_time = "0";
        s_time += "" + i_hour + sperate;
        if (i_minutes >= 0 && i_minutes < 10)
            s_time += "0";
        s_time += "" + i_minutes + sperate;
        if (i_seconds >= 0 && i_seconds < 10)
            s_time += "0";
        s_time += "" + i_seconds + "." + i_mill;

        return s_time;
    }

    public static int getDayCountFromYearMonth(int nYear, int nMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, nYear);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.MONTH, nMonth - 1);

        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static java.sql.Date changeMonth(java.sql.Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MONTH, month);

        // SimpleDateFormat sFmt = new SimpleDateFormat("YYYYMMDD");

        // return sFmt.format(cal.getTime());
        java.sql.Date newDate = new java.sql.Date(cal.getTime().getTime());

        return newDate;
    }

    public static Calendar getUTCCal() {
        Calendar utcCal = null;
        utcCal = Calendar.getInstance();

        // get the TimeZone for "America/Los_Angeles"
        TimeZone tz = TimeZone.getTimeZone("UTC");
        utcCal.setTimeZone(tz);

        return utcCal;
    }

    public static java.sql.Timestamp convert2UTCTimestamp(java.sql.Timestamp value) {
        // create a default calendar
        Calendar defaultCal = Calendar.getInstance();
        // set the time in the default calendar
        defaultCal.setTime(value);

        Calendar cal = getUTCCal();
        /*
         * Now we can pull the pieces of the date out of the default calendar
         * and put them into the user provided calendar
         */
        cal.set(Calendar.YEAR, defaultCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, defaultCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, defaultCal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, defaultCal.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, defaultCal.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, defaultCal.get(Calendar.SECOND));

        return new java.sql.Timestamp(cal.getTime().getTime());
    }

    public static java.sql.Date convert2UTCDate(java.sql.Date value) {
        // create a default calendar
        Calendar defaultCal = Calendar.getInstance();
        // set the time in the default calendar
        defaultCal.setTime(value);

        Calendar cal = getUTCCal();
        /*
         * Now we can pull the pieces of the date out of the default calendar
         * and put them into the user provided calendar
         */
        cal.set(Calendar.YEAR, defaultCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, defaultCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, defaultCal.get(Calendar.DAY_OF_MONTH));

        /*
         * This looks a little odd but it is correct - Calendar.getTime()
         * returns a Date...
         */

        return new java.sql.Date(cal.getTime().getTime());
    }

    public static java.sql.Date toSQLDate(java.sql.Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new java.sql.Date(milliseconds);
    }

    public static java.sql.Date toSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * 
     * @param timeZoneOffset
     *            int
     * @return String
     */
    public String getFormatedDateString(int timeZoneOffset) {
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
        if (ids.length == 0) {
            // if no ids were returned, something is wrong. use default TimeZone
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }

    public static String dateToString(Date date, String _timeZone) {
        if (date == null)
            return "";

        TimeZone timeZone = null;
        if (StringUtil.isEmpty(_timeZone)) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = TimeZone.getTimeZone(_timeZone);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        return DateUtil.dateToString(date, null);
    }

    public static String dateToString1(Date date) {
        if (date == null)
            return "";

        TimeZone timeZone = null;
        timeZone = TimeZone.getDefault();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);

    }

    public static String dateToString2(Date date) {
        if (date == null)
            return "";

        TimeZone timeZone = null;
        timeZone = TimeZone.getDefault();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);

    }

    public static String dateToString(java.sql.Date date, String _timeZone) {
        return DateUtil.dateToString(new Date(date.getTime()), _timeZone);
    }

    public static String dateToString(java.sql.Date date) {
        return DateUtil.dateToString(new Date(date.getTime()));
    }

    public static String timestampToString(Date date, String _timeZone) {
        if (date == null)
            return "";

        TimeZone timeZone = null;
        if (StringUtil.isEmpty(_timeZone)) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = TimeZone.getTimeZone(_timeZone);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);
    }

    public static String timestampToString4SSS(Date date) {
        if (date == null)
            return "";

        TimeZone timeZone = TimeZone.getDefault();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);
    }

    public static String timestampToDateString(Date date) {
        if (date == null)
            return "";

        TimeZone timeZone =  TimeZone.getDefault();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        return sdf.format(date);
    }

    public static String timestampToString(Date date) {
        return DateUtil.timestampToString(date, null);
    }

    public static String timestampToString(java.sql.Timestamp timestamp, String _timeZone) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);

        return DateUtil.timestampToString(new Date(milliseconds), _timeZone);
    }

    public static String timestampToString(java.sql.Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);

        return DateUtil.timestampToString(new Date(milliseconds));
    }

    public static String timestampToGMTString(java.sql.Timestamp timestamp) {
        if (timestamp == null)
            return "";

        TimeZone timeZone = null;
        timeZone = TimeZone.getTimeZone("GMT:00");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(timeZone);
        // TimeZone.setDefault(timeZone);

        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return sdf.format(new Date(milliseconds)) + " GMT";
    }

    public static java.sql.Date getMinDate() {
        return minDate;
    }

    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    public static java.sql.Date addDate(java.sql.Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return new java.sql.Date(c.getTime().getTime());
    }

    public static java.sql.Timestamp addTimeStamp(java.sql.Timestamp date, int day) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getMillis(new Date(date.getTime())) + ((long) day) * 24 * 3600 * 1000);

        return new java.sql.Timestamp(cal.getTime().getTime());
    }

    public static java.sql.Timestamp decreaseSysDateTime(java.sql.Timestamp date, int day) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getMillis(new Date(date.getTime())) + ((long) day) * 24 * 3600 * 1000);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        java.sql.Timestamp sysDateTime = new java.sql.Timestamp(cal.getTime().getTime());

        return sysDateTime;
    }

    public static java.sql.Timestamp addTimeStamp(java.sql.Timestamp date, double day) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getMillis(new Date(date.getTime())) + (long) (day * 24 * 3600 * 1000));

        return new java.sql.Timestamp(cal.getTime().getTime());
    }

    public static java.sql.Timestamp addTimeStampBySecond(java.sql.Timestamp date, int second) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getMillis(new Date(date.getTime())) + ((long) second) * 1000);

        return new java.sql.Timestamp(cal.getTime().getTime());
    }

    public static java.sql.Timestamp addTimeStampByHour(java.sql.Timestamp date, int hour) {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(getMillis(new Date(date.getTime())) + ((long) hour) * 3600 * 1000);

        return new java.sql.Timestamp(cal.getTime().getTime());
    }

    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    public static int diffHour(Date start, Date end) {
        return (int) ((getMillis(end) - getMillis(start)) / (3600 * 1000));
    }

    public static int diffHour(java.sql.Timestamp start, java.sql.Timestamp end) {
        return (int) ((getMillis(end) - getMillis(start)) / (3600 * 1000));
    }

    public static int diffMinute(Date start, Date end) {
        return (int) ((getMillis(end) - getMillis(start)) / (60 * 1000));
    }

    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static java.sql.Date stringToDate(String value, String fmt) {
        SimpleDateFormat sd = new SimpleDateFormat(fmt);

        Date date = null;

        try {
            date = sd.parse(value);

            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static java.sql.Date stringToDate(String value) {
        return stringToDate(value, "yyyyMMdd");
    }

    public static java.sql.Date getUTCBeginDate() {
        return stringToDate("19700101");
    }

    public static java.sql.Timestamp stringToTimestamp(String value, String fmt) {
        SimpleDateFormat sd = new SimpleDateFormat(fmt);

        Date date = null;

        try {
            date = sd.parse(value);

            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static java.sql.Timestamp stringToTimestamp(String value) {
        return stringToTimestamp(value, "yyyy-MM-dd HH:mm:ss");
    }

    public static java.sql.Timestamp stringToTimestamp4SSS(String value) {
        return stringToTimestamp(value, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static void main(String[] args) throws Exception {
        // TimeZone timeZone = TimeZone.getDefault();

        // System.out.println(timeZone.getDisplayName());

        /*
         * String[] ids = timeZone.getAvailableIDs(); for(int
         * i=0;i<ids.length;i++){ System.out.println(ids[i]); }
         */

        // System.out.println(TimeZone.getTimeZone("GMT+08:00").getID());
        Date nowDate = new Date();
        System.out.println(DateUtil.getHour(nowDate));

    }

}
