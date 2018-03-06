package com.commons.utils;
/**
 * 常量定义
 * @author csxx_wmw
 *
 */
public abstract class Constants {

	public final static String USERID_IN_COOKIE = "user_in_cookie";

	public final static String USER_ADMIN_IN_COOKIE = "user_admin_in_cookie";
	
	public final static String DATA_IN_COOKIE = "data_in_cookie";

	public static final Integer COOKIE_EXPIRY = 7 * 24 * 60 * 60;
	
	public static final int STATUS_AVAILABLE = 0;//有效 
	
	public static final int STATUS_UNAVAILABLE = 1;//无效
	
	public static final int OPTION_SUCCESS = 1; //操作成功
	
	public static final int OPTION_FAILED = 2;//操作失败
	
	public static final String INDEX_URL = "/front/index/index";
	
	public static final String REDICT_IDNEX_URL = "redirect:"+INDEX_URL;
	
	public static final String REDICT_LOGIN_URL = "/admin/login";
	
	/**
	 * 程序错误时发送邮件地址
	 */
	public static final String ERROR_MAIL_SEND_USER = "875345959@qq.com";
	/**
	 * 删除状态：正常
	 */
	public static final int DELETE_FLAG_NORMEL = 0;
	/**
	 * 删除状态：删除
	 */
	public static final int DELETE_FLAG_DELETE = 1;
	
	/**
	 * 设防
	 */
	public static final String SHE_FANG = "shefang";
	/**
	 * 撤防
	 */
	public static final String CHE_FANG = "chefang";
	/**
	 * 未绑定提醒
	 */
	public static final String UN_BING = "当前用户未绑定，请绑定后在操作！";
}
