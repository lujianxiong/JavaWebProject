package com.daxiong.book.util;
//时间处理工具类
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * @param date  util包下的时间
	 * @param pattern   时间格式
	 * @return  字符串形式的时间格式
	 */
	public static String dateToString(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	} 

}
