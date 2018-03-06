package com.commons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUserUtil {

	public static void saveUserToSession(HttpSession session,Object object,String userName){
		session.setAttribute(Constants.USERID_IN_COOKIE, object);
		session.setAttribute(UserUtil.KEY_USER, userName);
	}
	
	public static void saveUserToSession(HttpServletRequest request,Object object,String userName){
		saveUserToSession(request.getSession(), object, userName);
	}
	
	public static Object getUserToSession(HttpSession session){
		return session.getAttribute(Constants.USERID_IN_COOKIE);
	}
	
	public static Object getUserToSession(HttpServletRequest request){
		return getUserToSession(request.getSession());
	}
}
