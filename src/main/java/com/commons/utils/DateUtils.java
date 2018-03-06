package com.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**
	 * 获取当前时间，默认格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateFormat(){
		String farmat = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(farmat);
	}
	
	/**
	 * 获取当前时间 格式化
	 * @param format
	 * 			时间格式
	 * @return
	 */
	public static String getDateFormat(String format){
		return getDateFormat(format, new Date());
	}
	
	/**
	 * 获取任意时间格式化
	 * @param format
	 * 			时间格式
	 * @param date
	 * 			时间日期
	 * @return
	 */
	public static String getDateFormat(String format, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 时间戳转换成日期
	 * @param format 时间格式
	 * @param date 时间日期
	 * @return
	 */
	public static String getDateFormat(String format, long date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(date));
	}
	/**
	 * 根据时间戳获取时间
	 * @param date
	 * @return
	 */
	public static String getDateFormat(long date){
		String format = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(format,date);
	}
}
