package com.alpha.common.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alpha.common.base.Enums;

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
	public static String trim(String str) {
		return isNotEmpty(str) ? str.trim() : "";
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

	/**
	 * 一次性判断多个或单个对象为空。
	 * 
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (null == object || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();
	}

	/**
	 * 一次性判断多个或单个对象不为空。
	 * 
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static boolean isBlank(String... objects) {
		Object[] object = objects;
		return isBlank(object);
	}

	public static boolean isNotBlank(String... objects) {
		Object[] object = objects;
		return !isBlank(object);
	}

	public static boolean isBlank(String str) {
		Object object = str;
		return isBlank(object);
	}

	public static boolean isNotBlank(String str) {
		Object object = str;
		return !isBlank(object);
	}

	/**
	 * 判断一个字符串在数组中存在几个
	 * 
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr, String[] strings) {

		if (null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;

		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i;
	}

	/**
	 * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
	 * 
	 * @param args
	 * @return
	 */
	public static JSONObject isJSONObject(String args) {
		JSONObject result = null;
		if (isBlank(args)) {
			return result;
		}
		try {
			return JSONObject.parseObject(args.trim());
		} catch (Exception e) {
			return result;
		}
	}

	/**
	 * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
	 * 
	 * @param args
	 * @return
	 */
	public static JSONArray isJSONArray(Object args) {
		JSONArray result = new JSONArray();
		if (isBlank(args)) {
			return null;
		}
		if (args instanceof JSONArray) {

			JSONArray arr = (JSONArray) args;
			for (Object json : arr) {
				if (json != null && json instanceof JSONObject) {
					result.add(json);
					continue;
				} else {
					result.add(JSONObject.toJSON(json));
				}
			}
			return result;
		} else {
			return null;
		}

	}

	public static String trimToEmpty(Object str) {
		return (isBlank(str) ? "" : str.toString().trim());
	}

	/**
	 * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToGet(Map<? extends Object, ? extends Object> map) {
		String result = "";
		if (map == null || map.size() == 0) {
			return result;
		}
		Set<? extends Object> keys = map.keySet();
		for (Object key : keys) {
			result += ((String) key + "=" + (String) map.get(key) + "&");
		}

		return isBlank(result) ? result : result.substring(0, result.length() - 1);
	}

	/**
	 * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
	 * 
	 * @param args
	 * @return
	 */
	public static Map<String, ? extends Object> getToMap(String args) {
		if (isBlank(args)) {
			return null;
		}
		args = args.trim();
		// 如果是?开头,把?去掉
		if (args.startsWith("?")) {
			args = args.substring(1, args.length());
		}
		String[] argsArray = args.split("&");

		Map<String, Object> result = new HashMap<String, Object>();
		for (String ag : argsArray) {
			if (!isBlank(ag) && ag.indexOf("=") > 0) {

				String[] keyValue = ag.split("=");
				// 如果value或者key值里包含 "="号,以第一个"="号为主 ,如 name=0=3 转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

				String key = keyValue[0];
				String value = "";
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i] + "=";
				}
				value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
				result.put(key, value);

			}
		}

		return result;
	}

	/**
	 * 转换成Unicode
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		String as[] = new String[str.length()];
		String s1 = "";
		for (int i = 0; i < str.length(); i++) {
			int v = str.charAt(i);
			if (v >= 19968 && v <= 171941) {
				as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
				s1 = s1 + "\\u" + as[i];
			} else {
				s1 = s1 + str.charAt(i);
			}
		}
		return s1;
	}

	/**
	 * 合并数据
	 * 
	 * @param v
	 * @return
	 */
	public static String merge(Object... v) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < v.length; i++) {
			sb.append(v[i]);
		}
		return sb.toString();
	}

	/**
	 * 字符串转urlcode
	 * 
	 * @param value
	 * @return
	 */
	public static String strToUrlcode(String value) {
		try {
			value = java.net.URLEncoder.encode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			logger.error("字符串转换为URLCode失败,value:{}", value, e);
			return null;
		}
	}

	/**
	 * urlcode转字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String urlcodeToStr(String value) {
		try {
			value = java.net.URLDecoder.decode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			logger.error("URLCode转换为字符串失败;value:{}", value, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断字符串是否包含汉字
	 * 
	 * @param txt
	 * @return
	 */
	public static Boolean containsCN(String txt) {
		if (isBlank(txt)) {
			return false;
		}
		for (int i = 0; i < txt.length(); i++) {

			String bb = txt.substring(i, i + 1);

			boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
			if (cc)
				return cc;
		}
		return false;
	}

	/**
	 * 去掉HTML代码
	 * 
	 * @param news
	 * @return
	 */
	public static String removeHtml(String news) {
		String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");

		Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(s);
		String str = matcher.replaceAll("");

		Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
		Matcher matcher2 = pattern2.matcher(str);
		String strhttp = matcher2.replaceAll(" ");

		String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
				+ "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?" + "("
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
				+ "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*" + ")"
				+ "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?" + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
		Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
		Matcher matchhttp = p1.matcher(strhttp);
		String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");

		Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
		Matcher matchercomma = patterncomma.matcher(strnew);
		String strout = matchercomma.replaceAll(" ");
		String answer = strout.replaceAll("[\\pP‘’“”]", " ").replaceAll("\r", " ").replaceAll("\n", " ")
				.replaceAll("\\s", " ").replaceAll("　", "");

		return answer;
	}

	/**
	 * 把数组的空数据去掉
	 * 
	 * @param array
	 * @return
	 */
	public static List<String> array2Empty(String[] array) {
		List<String> list = new ArrayList<String>();
		for (String string : array) {
			if (StringUtils.isNotBlank(string)) {
				list.add(string);
			}
		}
		return list;
	}

	/**
	 * 把数组转换成set
	 * 
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if (null != id) {
				set.add(id);
			}
		}
		return set;
	}

	public static String toString(Serializable serializable) {
		if (null == serializable) {
			return null;
		}
		try {
			return (String) serializable;
		} catch (Exception e) {
			return serializable.toString();
		}
	}

	public static String toDecimalString(BigDecimal dc) {
		if (isBlank(dc)) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(dc);
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static String getDbName(String userName) {
		if (userName.startsWith("cn")) {
			return Enums.DbName.CN.getName();
		}
		return Enums.DbName.TW.getName();
	}

	public static boolean equalsDb(String userName1, String userName2) {
		if (isBlank(userName1) || isBlank(userName2)) {
			return false;
		}
		return userName1.substring(0, 2).equalsIgnoreCase(userName2.substring(0, 2));
	}
}
