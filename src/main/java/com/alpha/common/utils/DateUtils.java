package com.alpha.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	public static final String FMT_DATE_PATTERN = "yyyy-MM-dd";

	public static final String FMT_DATE_PATTERN_FILE = "yyyy_MM_dd";

	public static final String FMT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

	public static final String FMT_DATE_TIMES_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	private DateUtils() {
	}

	public static Date getTodayStart() {
		return getStartDateForDay(0);
	}

	public static Date getTodayEnd() {
		return getEndDateForDay(0);
	}

	/**
	 * 返回当前分钟数的最后一秒,hh:MM:59.900
	 * 
	 * @return
	 */
	public static Date getCurMinuteLastSecondTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 900);
		return cal.getTime();
	}

	public static String diffToHourAndMinuteString(Long preTime) {
		Date pre = new Date(preTime);
		Date now = new Date();
		long minutes = (pre.getTime() - now.getTime()) / (1000 * 60);
		StringBuilder sb = new StringBuilder();
		if (minutes > 60) {
			if (minutes % 60 == 0) {
				sb.append(minutes / 60).append("小时");
			} else {
				sb.append(minutes / 60).append("小时").append(minutes % 60).append("分钟");
			}
		} else {
			sb.append(minutes % 60).append("分钟");
		}
		return sb.toString();
	}

	public static String diffToDayOrHourOrMinuteString(Long preTime) {
		Date pre = new Date(preTime);
		Date now = new Date();
		long s = Math.abs(pre.getTime() - now.getTime()) / 1000;
		long count;
		StringBuilder fmt = new StringBuilder();
		if ((count = s / (60 * 60 * 24)) > 0) {
			fmt.append(count);
			fmt.append("天");
		} else if ((count = s / (60 * 60)) > 0) {
			fmt.append(count);
			fmt.append("小时");
		} else if ((count = s / 60) > 0) {
			fmt.append(count);
			fmt.append("分钟");
		}
		return fmt.toString();
	}

	public static int getDayDiff(Date start, Date end) {
		return (int) ((end.getTime() - start.getTime()) / 1000 / 60 / 60 / 24);
	}

	/**
	 * 大于1小时显示小时分钟数，小于1小时显示分钟数<br>
	 * 
	 * @param hours小时数
	 * @return
	 */
	public static String getDateTipsPattern(long minutes) {
		StringBuilder sb = new StringBuilder();
		if (minutes > 60) {
			if (minutes % 60 == 0) {
				sb.append(minutes / 60).append("小时");
			} else {
				sb.append(minutes / 60).append("小时").append(minutes % 60).append("分钟");
			}
		} else {
			sb.append(minutes % 60).append("分钟");
		}
		return sb.toString();
	}

	/**
	 * 获取动态时间间隔现在的时间的间隔天数
	 */
	public static long getStoreDynaDay(long dynaDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long nowTimeInMillis = cal.getTimeInMillis();

		cal.setTimeInMillis(dynaDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long dynaDateTime = cal.getTimeInMillis();
		long times = Math.abs(nowTimeInMillis - dynaDateTime);
		long day = times / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 获取动态时间间隔现在的时间的间隔小时
	 */
	public static long getStoreDynaHour(long dynaDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long nowTimeInMillis = cal.getTimeInMillis();

		cal.setTimeInMillis(dynaDate);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long dynaDateTime = cal.getTimeInMillis();
		long times = Math.abs(nowTimeInMillis - dynaDateTime);
		long day = times / (60 * 60 * 1000);
		return day;
	}

	/**
	 * 获取动态时间间隔现在的时间的间隔小时
	 */
	public static long getStoreDynaMinute(long dynaDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long nowTimeInMillis = cal.getTimeInMillis();

		cal.setTimeInMillis(dynaDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long dynaDateTime = cal.getTimeInMillis();
		long times = Math.abs(nowTimeInMillis - dynaDateTime);
		long day = times / (60 * 1000);
		return day;
	}

	public static Date getWeekStart() {
		return getWeekStart(new Date());
	}

	public static Date getWeekStart(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int weekday = calender.get(Calendar.DAY_OF_WEEK);
		calender.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		if (weekday == Calendar.SUNDAY) {
			calender.add(Calendar.DATE, -7);
		}
		truncate(calender);
		return calender.getTime();
	}

	/**
	 * 获取今天所在周最晚的时间，即周日的23点59分59秒
	 * 
	 * @param standard 基准时间
	 * @return 指定时间所在周最晚的时间，即周日的23点59分59秒
	 */
	public static Date getWeekEnd() {
		return getWeekEnd(new Date());
	}

	/**
	 * 获取指定时间所在周最晚的时间，即周日的23点59分59秒
	 * 
	 * @param standard 基准时间
	 * @return 指定时间所在周最晚的时间，即周日的23点59分59秒
	 */
	public static Date getWeekEnd(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int weekday = calender.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.SUNDAY) {
			calender.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calender.add(Calendar.DATE, 7);
		}
		finalTime(calender);
		return calender.getTime();
	}

	/**
	 * 获取当天日期前后的某一天的开始时间
	 */
	public static Date getStartDateForDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当天日期前后的某一天的开始时间
	 */
	public static Date getStartDateForDay(int day, int houer) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, houer);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的开始时间
	 */
	public static Date getStartDateByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的开始时间
	 */
	public static Date getStartDateByDate(long timeInMillis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当天日期前后的某一天的结束时间
	 */
	public static Date getEndDateForDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的结束时间
	 */
	public static Date getEndDateByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取当前日期的结束时间
	 */
	public static Date getEndDateByDate(long timeInMillis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取指定周的开始时间
	 */
	public static Date getWeekFirstDay(int week) {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.WEEK_OF_YEAR, week);

		int weekday = calender.get(Calendar.DAY_OF_WEEK);
		calender.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		if (weekday == Calendar.SUNDAY) {
			calender.add(Calendar.DATE, -7);
		}
		truncate(calender);
		return calender.getTime();
	}

	/**
	 * 获取指定周的最后时间
	 */
	public static Date getWeekEndDay(int week) {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.WEEK_OF_YEAR, week);

		int weekday = calender.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.SUNDAY) {
			calender.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calender.add(Calendar.DATE, 7);
		}
		finalTime(calender);
		return calender.getTime();
	}

	/**
	 * 获取指定月份的开始时间(month为当前月的偏移量)
	 */
	public static Date getMonthFirstDay(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定月份的最后时间(month为当前月的偏移量)
	 */
	public static Date getMonthEndDay(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定月份的开始时间(month为当前月的偏移量)
	 */
	public static Date getMonthFirstDay(Date now, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定月份的最后时间(month为当前月的偏移量)
	 */
	public static Date getMonthEndDay(Date now, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定年，月份的开始时间
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getYearMonthFirstDay(int year, int month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定年的开始时间
	 */
	public static Date getYearFirstDay(int year) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定年的最后时间
	 */
	public static Date getYearEndDay(int year) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year + 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取年月日
	 */
	public static Date getYmd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取年份
	 */
	public static Integer getYearForDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 */
	public static Integer getMonthForDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期对应的天数
	 */
	public static Integer getDayForDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 时间处理,将Calendar的add方法封装，提供统一的工具方法
	 */
	public static Date add(Date date, int timeField, int number) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(timeField, number);
		return calendar.getTime();
	}

	/**
	 * 获取执行类型时间值，比如获取时间的小时数，秒数，一个月的第几天等等<br>
	 */
	public static int fieldValue(Date date, int fieldType) {
		if (date == null)
			throw new NullPointerException();
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		return calender.get(fieldType);
	}

	/**
	 * 时间处理,将Calendar的add方法封装，提供统一的工具方法
	 */
	public static Date setField(Date date, int timeField, int number) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(timeField, number);
		return calendar.getTime();
	}

	/*---------------------------------------------------------------- 时间转换 ------------------------------------------------*/

	/**
	 * 将日期格式化为yyyy-MM-dd HH:mm:ss的日期字符串
	 * 
	 * @param date 需要格式化的日期
	 * @return yyyy-MM-dd HH:mm:ss的日期字符串
	 */
	public static String format(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_DATE_TIMES_PATTERN);
		return sdf.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date 日期
	 * @param fmt 格式
	 * @return
	 */
	public static String formatString(Date date, String fmt) {
		if (date == null) {
			return null;
		}
		if (StringUtils.equalsIgnoreCase(fmt, FMT_DATE_PATTERN)) {
			SimpleDateFormat YMD_FMT = new SimpleDateFormat(FMT_DATE_PATTERN);
			return YMD_FMT.format(date);
		} else if (StringUtils.equalsIgnoreCase(fmt, FMT_DATE_TIME_PATTERN)) {
			SimpleDateFormat YMD_HM_FMT = new SimpleDateFormat(FMT_DATE_TIME_PATTERN);
			return YMD_HM_FMT.format(date);
		} else if (StringUtils.equalsIgnoreCase(fmt, FMT_DATE_TIMES_PATTERN)) {
			SimpleDateFormat YMD_HMS_FMT = new SimpleDateFormat(FMT_DATE_TIMES_PATTERN);
			return YMD_HMS_FMT.format(date);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(date);
		}
	}

	public static Date parse(String str, String formatParam) {
		try {
			String format = formatParam;
			if (StringUtils.isEmpty(str))
				return null;
			if (StringUtils.isEmpty(format))
				format = FMT_DATE_TIMES_PATTERN;

			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(str);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取指定日期的秒数
	 */
	public static long seconds(Date date) {
		if (date == null)
			return 0L;
		return milliseconds(date) / 1000;
	}

	/**
	 * 获取指定日期自1970-01-01的偏移天数
	 * 
	 * @param date
	 * @return 指定日期自1970-01-01的偏移天数，如果日期为空，则返回0.
	 */
	public static Integer days(Date date) {
		if (date == null)
			return 0;
		return (int) (milliseconds(date) / 86400000L);
	}

	/**
	 * 获取指定日期的毫秒数
	 */
	public static long milliseconds(Date date) {
		if (date == null) {
			return 0L;
		}
		return date.getTime();
	}

	/**
	 * 获取基准时间按照指定粒度偏移offset后的时间范围，如今天所在的月的开始时间和结束时间等等<br>
	 * 注意：目前暂时只支持天、周、月粒度
	 * 
	 * @param stadard 基准时间
	 * @param granularity 时间粒度,详细see {@link Calendar},如Calendar.WEEK_OF_YEAR
	 * @param offset 偏移单位数
	 * @return 取基准时间按照指定粒度偏移offset后的时间范围
	 */
	public static Date[] rangeTime(Date standard, int granularity, int offset) {
		if (granularity == Calendar.DATE)
			return handleDayRange(standard, offset);
		else if (granularity == Calendar.WEEK_OF_YEAR)
			return handleWeekRange(standard, offset);
		else if (granularity == Calendar.MONTH)
			return handleMonthRange(standard, offset);
		else if (granularity == Calendar.HOUR_OF_DAY)
			return handleHourRange(standard, offset);
		throw new IllegalArgumentException();
	}

	/**
	 * 获取从指定时间偏移一定时间后再一个周期内的开始和结束时间。<br>
	 * 如获取上周的开始结束时间：rangTime(new Date(),Calendar.DATE,-1,1)<br>
	 * 目前暂时只支持天的偏移等
	 * 
	 * @param date 基准时间
	 * @param calendarField 时间粒度类型 see {@link Calendar}
	 * @param offset 偏移时间
	 * @param duration 时间周期长度
	 * @return
	 */
	public static Date[] rangeTimes(Date dateParam, int offset, int duration) {
		Date[] dateTimes = new Date[2];
		Date date = new Date(dateParam.getTime());
		if (offset != 0)
			date = DateUtils.add(date, Calendar.DATE, offset);
		date = DateUtils.truncate(date);
		dateTimes[0] = date;
		if (duration > 1)
			date = DateUtils.add(date, Calendar.DATE, duration);
		date = DateUtils.finalTime(date);
		dateTimes[1] = date;
		return dateTimes;
	}

	/**
	 * 给定基准时间,并且偏移一定天数，得出时间范围
	 * 
	 * @param stadard 基准时间
	 * @param offset 偏移天数
	 * @return 基础时间偏移指定天数后的时间范围
	 */
	public static Date[] handleDayRange(Date standard, int offset) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(standard);
		Date[] dates = new Date[2];
		if (offset != 0)
			calender.add(Calendar.DATE, offset);
		truncate(calender);
		dates[0] = calender.getTime();
		finalTime(calender);
		dates[1] = calender.getTime();
		return dates;
	}

	/**
	 * 给定基准时间,并且偏移一定周数，得出时间范围
	 * 
	 * @param stadard 基准时间
	 * @param offset 偏移周数
	 * @return 基础时间偏移指定周数后的时间范围
	 */
	public static Date[] handleWeekRange(Date standard, int offset) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(standard);
		Date[] dates = new Date[2];
		if (offset != 0)
			calender.add(Calendar.WEEK_OF_YEAR, offset);
		int weekday = calender.get(Calendar.DAY_OF_WEEK);
		if (weekday == Calendar.SUNDAY) {
			calender.add(Calendar.DATE, -7);
		}
		calender.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		truncate(calender);
		dates[0] = calender.getTime();
		calender.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calender.add(Calendar.DATE, 7);
		finalTime(calender);
		dates[1] = calender.getTime();
		return dates;
	}

	/**
	 * 给定基准时间,并且偏移一定月数，得出时间范围
	 * 
	 * @param stadard 基准时间
	 * @param offset 偏移月数
	 * @return 基础时间偏移指定月数后的时间范围
	 */
	public static Date[] handleMonthRange(Date standard, int offset) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(standard);
		Date[] dates = new Date[2];
		if (offset != 0)
			calender.add(Calendar.MONTH, offset);
		calender.set(Calendar.DAY_OF_MONTH, 1);
		truncate(calender);
		dates[0] = calender.getTime();
		calender.add(Calendar.MONTH, 1);
		calender.add(Calendar.DATE, -1);
		finalTime(calender);
		dates[1] = calender.getTime();
		return dates;
	}

	private static Date[] handleHourRange(Date standard, int offset) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(standard);
		Date[] dates = new Date[2];
		if (offset != 0)
			calender.add(Calendar.HOUR_OF_DAY, offset);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		calender.set(Calendar.MILLISECOND, 0);
		dates[0] = calender.getTime();
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.SECOND, 59);
		calender.set(Calendar.MILLISECOND, 999);
		dates[1] = calender.getTime();
		return dates;
	}

	public static Date truncate(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		truncate(calender);
		return calender.getTime();
	}

	public static void truncate(Calendar calender) {
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		calender.set(Calendar.MILLISECOND, 0);
	}

	public static Date finalTime(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		finalTime(calender);
		return calender.getTime();
	}

	public static void finalTime(Calendar calender) {
		calender.set(Calendar.HOUR_OF_DAY, 23);
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.SECOND, 59);
		calender.set(Calendar.MILLISECOND, 999);
	}

	public static String diffToString(Long preTime) {
		Date pre = new Date(preTime);
		Date now = new Date();
		long s = (now.getTime() - pre.getTime()) / 1000;
		long count;
		StringBuilder fmt = new StringBuilder();
		if ((count = s / (3600 * 24 * 365)) > 0) {
			fmt.append(count);
			fmt.append("年");
			s -= count * (3600 * 24 * 365);
		}
		if ((count = s / (3600 * 24 * 30)) > 0) {
			fmt.append(count);
			fmt.append("个月");
		}
		if (fmt.length() == 0) {
			fmt.append("小于一个月");
		}
		return fmt.toString();
	}

	/**
	 * 两个日期相隔的天数<br>
	 * 
	 * @param fromDate 开始日期
	 * @param toDate 结束日期
	 * @return 如果为-1则传入参数有为空，否则为两个日期相隔的天数取绝对值
	 */
	public static int daysBetween(Date fromDateParam, Date toDateParam) {
		if (fromDateParam == null || toDateParam == null)
			return -1;
		Date fromDate = new Date(fromDateParam.getTime());
		Date toDate = new Date(toDateParam.getTime());
		fromDate = truncate(fromDate);
		toDate = truncate(toDate);

		return (int) Math.abs((toDate.getTime() - fromDate.getTime()) / 86400000L) + 1;
	}

	public static boolean isOneDays(Date fromDateParam, Date toDateParam) {
		if (fromDateParam == null || toDateParam == null)
			return false;
		Date fromDate = new Date(fromDateParam.getTime());
		Date toDate = new Date(toDateParam.getTime());
		fromDate = truncate(fromDate);
		toDate = truncate(toDate);
		return fromDate.getTime() == toDate.getTime();
	}

	/**
	 * 长整开转换为Date
	 * 
	 * @param dateString 时间长long形
	 * @return
	 */
	public static Date convertLongToDate(String dateString) {
		if (StringUtil.isEmpty(dateString)) {
			return null;
		}
		return new Date(Long.parseLong(dateString));
	}

	public static void main(String[] args) throws Exception {
		Date startTime = parse("2016-12-31 00:00:00", FMT_DATE_TIMES_PATTERN);
		Date startTime1 = parse("2017-01-01 00:00:00", FMT_DATE_TIMES_PATTERN);
		logger.info(DateUtils.getYearForDate(startTime) + "年" + DateUtils.getMonthForDate(startTime) + "月");
		logger.info(DateUtils.getYearForDate(startTime1) + "年" + DateUtils.getMonthForDate(startTime1) + "月");
		Date monthStartTime = DateUtils.getYearMonthFirstDay(DateUtils.getYearForDate(startTime),
				DateUtils.getMonthForDate(startTime));
		Date monthStartTime1 = DateUtils.getYearMonthFirstDay(DateUtils.getYearForDate(startTime1),
				DateUtils.getMonthForDate(startTime1));
		logger.info(DateUtils.formatString(monthStartTime, FMT_DATE_TIMES_PATTERN));
		logger.info(DateUtils.formatString(monthStartTime1, FMT_DATE_TIMES_PATTERN));

	}
}
