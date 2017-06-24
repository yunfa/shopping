package com.alpha.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

    private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static final String SEPARATOR_EMPTY = "";

    /**
     * 竖线条
     */
    public static final String SEPARATOR_VERT_LINE = "|";

    private StringUtil() {

    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * 
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtil.isEmpty(source)) {
            return "";
        }
        StringBuilder buf = new StringBuilder(source.length());
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNoEmoji(codePoint)) {
                buf.append(codePoint);
            }
        }
        return buf.toString();
    }

    private static boolean isNoEmoji(char codePoint) {
        if (codePoint == 0x0) {
            return true;
        }
        if (codePoint == 0x9) {
            return true;
        }
        if (codePoint == 0xA) {
            return true;
        }
        if (codePoint == 0xD) {
            return true;
        }
        if ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) {
            return true;
        }
        if ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) {
            return true;
        }
        if ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)) {
            return true;
        }
        return false;
    }

    public static String urlDecode(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        String temp = "";
        try {
            temp = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("urlDecode错误", e);
        }
        return temp;
    }

    public static String replaceEmpty(String str) {
        if (str != null && str.trim().length() > 0) {
            return str.replace(" ", "");
        }
        return "";
    }

    public static String convertDatePatten(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String convertTimeStringPattern(String utime, String oldPattern, String newPattern) {
        logger.debug("oldtime:" + utime);
        SimpleDateFormat format1 = new SimpleDateFormat(oldPattern);
        SimpleDateFormat format2 = new SimpleDateFormat(newPattern);
        Date date = null;
        String newString = "";
        try {
            date = format1.parse(utime);
            newString = format2.format(date);
        } catch (ParseException e) {
            logger.error("时间转换错误", e);
        }
        return newString;
    }

    public static String getRandomCode(int maxnum, int bitcount) {
        Random rd = new Random();
        int num = rd.nextInt(maxnum);
        logger.debug("num=" + num);
        String str = Integer.toString(num);
        int len = str.length();
        for (int i = 0; i < (bitcount - len); i++) {
            str = "0" + str;
        }
        return str;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0 || "null".equalsIgnoreCase(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String getFirstUpperString(String str) {
        String tmp = str.substring(0, 1).toUpperCase();
        return tmp + str.toLowerCase().substring(1, str.length());
    }

    /**
     * 字符串如果非空则返回原字符串 ，如果为空则返回""空字符。避免null。
     * 
     * @param str
     * @return
     */
    public static String trimNullToString(String str) {
        return isNotEmpty(str) ? str : "";
    }

    /**
     * 合并
     * 
     * @param separator
     * @param vals
     * @return
     */
    public static String join(String separator, String... vals) {
        if (null == separator || null == vals)
            return null;
        StringBuilder rst = new StringBuilder();
        for (String val : vals) {
            if (isEmpty(val))
                continue;
            rst.append(val).append(separator);
        }
        return rst.substring(0, rst.length() - separator.length());
    }

    /**
     * 拼接字符串
     * 
     * @param collection 集合
     * @param separator 分隔符号
     * @return
     */
    public static String join(Collection<?> collection, String separator) {
        String serviceCitys = StringUtils.join(collection, separator);
        if (isNotEmpty(serviceCitys)) {
            return separator + serviceCitys + separator;
        }
        return "";
    }

    /**
     * 将对象转化为字符串
     * 
     * @param obj 对象
     * @return
     */
    public static String valueOf(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return String.valueOf(date.getTime());
        }
        return obj.toString();
    }

    public static String hideCellphone(String cellphone) {
        if (cellphone == null) {
            return "";
        }
        int length = cellphone.length();
        if (length <= 7) {
            return cellphone;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i >= 3 && i < 7) {
                sb.append("*");
            } else {
                sb.append(cellphone.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String getHouseType(Integer ting, Integer shi) {
        if (ting == null && shi == null) {
            return "";
        }
        return (shi == null ? "" : shi) + "室" + (ting == null ? "" : ting) + "厅";
    }

    public static String getHouseArea(BigDecimal area) {
        if (area == null) {
            return "";
        }
        return area.toString() + "平";
    }
}
