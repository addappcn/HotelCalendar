package cn.hotelcal.addapp.hotelcalendar;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author matt
 * blog addapp.cn
 * email addappcn@gmail.com
 * date 2017-05-26
 * description
 */
public class Common {
    public static final String dateFormatter_yyyy_mm =  "yyyy年MM月";
    public static final String dateFormatter =  "yyyy-MM-dd E";
    public static final String dateFormatter1 =  "yyyy-MM-dd";
    public static final String dateFormatter_MM_DD =  "MM月dd日";
    public static final String dateFormatter_DD =  "dd";
    public static final String dateFormattermm =  "yyyy-MM-dd HH:mm";
    public static final SimpleDateFormat FORMATTER_yyyy_mm = new SimpleDateFormat(Common.dateFormatter_yyyy_mm, Locale.CHINA);
    public static final SimpleDateFormat FORMATTER1 = new SimpleDateFormat(Common.dateFormatter1, Locale.CHINA);
    public static final SimpleDateFormat FORMATTER_MM_DD = new SimpleDateFormat(Common.dateFormatter_MM_DD, Locale.CHINA);
    public static final SimpleDateFormat FORMATTER_WITH_E = new SimpleDateFormat(Common.dateFormatter, Locale.CHINA);
    public static final SimpleDateFormat FORMATTER_WITH_DD = new SimpleDateFormat(Common.dateFormatter_DD, Locale.CHINA);
    public static final SimpleDateFormat FORMATTER_WITH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
}
