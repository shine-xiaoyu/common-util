package com.xqy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @Description:日期工具类   
 * @date:   2019年12月5日 下午1:23:19
 */
public class DateUtil {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: format   
	 * @Description: 时间格式化  
	 * @param: @param theDate
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String format(Date theDate) {
		return dateFormat.format(theDate);
	}
	
	/**
	 * 根据生日计算年龄
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date birthDate) {
		//获得日历控件
		Calendar calendar = Calendar.getInstance();
		//获得年、月、日
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH);
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		//设置日历控件为生日的时间
		calendar.setTime(birthDate);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
		//计算年龄
		int age = nowYear-birthYear;
		//如果生日的月份大于当前月份时，年龄-1
		if(birthMonth>nowMonth) {
			age--;
		}
		//如果月份相等，判断日期
		if(birthMonth==nowMonth && nowDay<birthDay) {
			age--;
		}
		return age;
	}
	/**
	 * 根据出生日期计算年龄
	 * @param birthDateStr "2019-11-08"
	 * @return
	 */
	public static int getAge(String birthDateStr) {
		Date birthDate = null;
		try {
			//解析日期字符串为Date对象
			birthDate = dateFormat.parse(birthDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//调用日期计算方法
		return getAge(birthDate);
	}
	/**
	 * @Title: getDayNum   
	 * @Description: 获取开始日期和结束日期之间有多少天   
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(Date date1,Date date2) {
		//一天有多少毫秒
		Long dayTime = 1000*60*60*24L;
		Long startTime = date1.getTime();
		Long endTime = date2.getTime();
//		System.out.println(startTime);
//		System.out.println(endTime);
		Double dayNum = Math.abs(((endTime-startTime)/dayTime*1.0));
//		dayNum = Math.ceil(dayNum);
//		System.out.println(dayNum);
		return dayNum.intValue()+1;
	}
	/**
	 * @Title: getDayNum   
	 * @Description: 计算指定日期距离今天，过去了多少天或还有多少天   
	 * @param: @param date
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(Date date) {
		Date date2 = new Date();
		return getDayNum(date,date2);
	}
	/**
	 * @Title: isToday   
	 * @Description: 验证指定日期是否为今天   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isToday(Date theDate) {
		Date nowDate = new Date();
		String nowDateStr = dateFormat.format(nowDate);
		String theDateStr = dateFormat.format(theDate);
		return nowDateStr.equals(theDateStr);
	}
	/**
	 * @Title: isToday   
	 * @Description: 验证指定日期是否为今天    
	 * @param: @param theDateStr "2019-11-30"
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isToday(String theDateStr) {
		try {
			Date theDate = dateFormat.parse(theDateStr);
			return isToday(theDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @Title: isInWeek   
	 * @Description: 判断指定日期是否在本周   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isInWeek(Date theDate) {
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		//本周的第几天
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		//设置本周第一天的时间
		c.add(Calendar.DAY_OF_YEAR, 1-dayofweek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date firstDate = c.getTime();
		System.out.println(dateTimeFormat.format(firstDate));
		//设置本周最后一天的时间
		c.add(Calendar.DAY_OF_YEAR, 6);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date lastDate = c.getTime();
		System.out.println(dateTimeFormat.format(theDate));
		System.out.println(dateTimeFormat.format(lastDate));
		return compareTime(theDate,firstDate)>=0 && compareTime(theDate,lastDate)<=0;
	}
	/**
	 * @Title: getFirstDateInMonth   
	 * @Description: 获取指定日期月份的第一天 
	 * 2019-12-04 12:22:45  -> 2019-12-01 00:00:00
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static Date getFirstDateInMonth(Date theDate) {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		String dateStr = format.format(theDate);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		Calendar c = Calendar.getInstance();
		c.setTime(theDate);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * @Title: getLastDateInMonth   
	 * @Description: 获得指定日期的最后一天    
	 * 2019-12-04 12:22:45  -> 2019-12-31 23:59:59
	 * @param: @param theDate
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getLastDateInMonth(Date theDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(theDate);
		c.add(Calendar.MONTH, 1);
		Date firstDateInMonth = getFirstDateInMonth(c.getTime());
		c.setTime(firstDateInMonth);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}
	/**
	 * @Title: compareTime   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int compareTime(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
		
	}
	
	public static void main(String[] args) throws ParseException {
		Date firstDateInMonth = getLastDateInMonth(new Date());
		System.out.println();
		Date theDate = dateFormat.parse("2019-12-20");
		System.out.println(isInWeek(theDate));
	}
}
