package com.commons.utils;

public class StringUtils {

	/**
	 * 检测Str是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return str == null || "".equals(str.trim());
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}

	public static boolean hasText(String string) {
		// TODO Auto-generated method stub
		return org.springframework.util.StringUtils.hasText(string);
	}
}
