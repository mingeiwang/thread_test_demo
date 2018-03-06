package com.commons.utils;

public abstract class Utils {

	public static String getNonceStr(int length){
		String string = "qwertyuioplkjhgfdsazxcvbnm123456789POIUYTREWQASDFGHJKLMNBVCXZ";
		StringBuffer sb = new StringBuffer();
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(getRandom(len-1)));
	    }
	    return sb.toString();
	}
	/**
	 * 获取随机数，[0,i)
	 * @param i
	 * @return
	 */
	public static int getRandom(int i){
		return (int) (Math.random()*i);
	}
}
