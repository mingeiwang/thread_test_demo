package com.commons.utils;

import org.springframework.context.MessageSource;

/**
 * 国际化信息帮助类
 * @author csxx_wmw
 *
 */
public class MessageUtil {

	private static MessageSource resources;
	
	public static void setResources(MessageSource resources) {
		MessageUtil.resources = resources;
	}
	
	public static String getMessage(String msgKey, Object... args){
		return resources.getMessage(msgKey, args, UserUtil.getLocale());
	}
}
