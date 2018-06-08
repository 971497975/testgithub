package tellhow.cavate.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//时间处理工具类
public class DateUtil {

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CANADA);

	// 1.获取当年年份，字符串形式    "2016"
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	// 2.获取当前年月日,字符串形式  "2016-12-05"
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	// 3.获取当前年月日    "20161205"
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	// 4.获取当前时间 "2016-12-05 17:10:38"
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	// 5.把输入的日期格式的时间转换为字符串型 "2016-12-05 17:10:38"
	public static String ChangeTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return sdf.format(date);
	}

	// 6.把输入的日期格式的时间转换为字符串型 "2016-12-05"
	public static String ChangeTime2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return sdf.format(date);
	}

	// 7.比较两个字符串类型的日期数据，如果前者比后者大，返回true
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	// 8.字符串形式的日期数据，转换为date形式"2016-12-05">>>>Mon Dec 05 00:00:00 CST 2016
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 9.判断输入的字符串形式的日期，转换后是否真的是有效的日期
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 10.获取两个字符串形式的日期之间间隔年数 2015-12-04 2016-12-04 
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			return 0;
		}
	}

	// 11.获取两个字符串形式的日期之间间隔天数 2015-12-04 2016-12-06 
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 12.当前日期加上传进来的字符日期得到 2016-12-28 08:28:51
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, daysInt);
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	// 13.获取多少天后是星期几
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, daysInt);
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	// 14.传进来的日期格式时间转为字符串格�?2016-12-06 08:38:54
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return sdf.format(date);
	}

	// "yyyy-MM-dd"字符串型的日期格式数据加�? HH:mm:ss",再转化成Date�?
	public static Date fomatDateAddDay(String date) {
		if ("".equals(date) || date == null) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(date + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 字符串形式的日期数据，转换为date�?"2016-12-05">>>>Mon Dec 05 00:00:00 CST 2016
	public static Date string2Date(String date) {
		if ("".equals(date) || date == null) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//获取前三个月
	public static String getThreeMonth(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
	    c.add(Calendar.MONTH, -3);
	    Date m3 = c.getTime();
	    String mon3 = sdfDay.format(m3);
	    return mon3;
	}
	
	//获取两个日期之间的所有日期
	public static List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
     while(begin.getTime()<=end.getTime()){
         result.add(tempStart.getTime());
         tempStart.add(Calendar.DAY_OF_YEAR, 1);
         begin = tempStart.getTime();
     }
        return result;
}

}
