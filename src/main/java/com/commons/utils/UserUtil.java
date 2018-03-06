package com.commons.utils;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.MDC;

import com.commons.exception.UnloginException;


public class UserUtil {

	private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

	private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
		protected Locale initialValue() {
			// 语言的默认值
			return Locale.SIMPLIFIED_CHINESE;
		};
	};

	public static final String KEY_LANG = "lang";

	public static final String KEY_USER = "user";

	public static void setUser(String userid) {
		tlUser.set(userid);

		// 把用户信息放到slf4j
		MDC.put(KEY_USER, userid);
	}

	/**
	 * 如果没有登录，返回null
	 * 
	 * @return
	 */
	public static String getUserIfLogin() {
		return tlUser.get();
	}

	/**
	 * 如果没有登录会抛出异常
	 * 
	 * @return
	 */
	public static String getUser() {
		String user = tlUser.get();

		if (user == null) {
			throw new UnloginException();
		}

		return user;
	}

	public static void setLocale(String locale) {
		setLocale(new Locale(locale));
	}

	public static void setLocale(Locale locale) {
		tlLocale.set(locale);
	}

	public static Locale getLocale() {
		return tlLocale.get();
	}

	public static void clearAllUserInfo() {
		tlUser.remove();
		tlLocale.remove();

		MDC.remove(KEY_USER);
	}
	
	/**
	 * 存入User
	 * @param userName
	 * @param session
	 */
	public static void setUserNameToSession(String userName,HttpSession session){
		session.setAttribute(KEY_USER, userName);
	}
	
	/**
	 * 获取User
	 * @param session
	 * @return
	 */
	public static String getUserNameBySession(HttpSession session){
		return (String) session.getAttribute(KEY_USER);
	}
}
