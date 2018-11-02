package com.example.common.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 最好用的工具类
 */
public class NbUtil {

	
	
	// 时间处理

	/**
	 * 返回指定时间的YYYY-MM-dd hh:mm:ss 字符串格式
	 */
	public static String getDTString(Date d){
		return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(d);
	}
	
	/**
	 * 返回系统当前时间的YYYY-MM-dd hh:mm:ss 字符串格式
	 */
	public static String getNow(){
		return getDTString(new Date());
	}
	
	/**
	 * 将一个字符串转换为日期格式（YYYY-MM-dd HH:mm:ss）
	 */
	public static Date getDT(String d) throws ParseException{
		return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse(d);
	}
	
	/**
	 * 返回当前时间的指定形式 如（YYYY_MM_dd_HH_mm_ss）
	 * @return
	 */
	public static String getNowString(String geshi) {
		return new SimpleDateFormat(geshi).format(new Date());
	}
	
	/**
	 * 获取指定日期的1号
	 */
	public static Date getYueOne(Date date) {
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.set(Calendar.HOUR_OF_DAY, 0);  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        return calendar.getTime();  
	}
	
	
	
	//基本字符处理

	/**
	 * 该字符串是否为null或者空串
	 */
	public static boolean isNull(String str) {
		return (str == null || str.equals(""));
	}
	
	/**
	 * 如果一个字符串为(null,"","null")，则转化为指定值
	 */
	public static String toStr(String str, String toStr) {
		if (str == null || str.equals("") || str.equals("null")) {
			return toStr;
		}
		return str;
	}

	/**
	 * 如果该货不能转成一个数字，则返回指定值
	 */
	public static Integer toInt(String str, Integer toInt) {
		try {
			return new Integer(str);
		} catch (Exception e) {
			return toInt;
		}
	}

	/**
	 * 将一个字符串ISO-8859-1转码UTF-8
	 */
	public static String toUtf8(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 将字符串转化为指定数据类型
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getObjectByClass(String str, Class<T> cs){
		Object value = null;
		if(str == null){
			value = null;
		}else if (cs.equals(String.class)) {
			value = str;
		} else if (cs.equals(int.class)||cs.equals(Integer.class)) {
			value = new Integer(str);
        } else if (cs.equals(long.class)||cs.equals(Long.class)) {
        	value = new Long(str);
        } else if (cs.equals(short.class)||cs.equals(Short.class)) {
        	value = new Short(str);
        } else if (cs.equals(float.class)||cs.equals(Float.class)) {
        	value = new Float(str);
        } else if (cs.equals(double.class)||cs.equals(Double.class)) {
        	value = new Double(str);
        } else if (cs.equals(boolean.class)||cs.equals(Boolean.class)) {
        	value = new Boolean(str);
        }else{
        	throw new RuntimeException("超纲的类型：" + cs + "，未能转换值：" + str);
        }
		return (T)value;
	}
	
	
	/**
	 * 返回唯一标示28位唯一标示符
	 */
	public static String getMarking28() {
		return System.currentTimeMillis()+""+new Random().nextInt(Integer.MAX_VALUE);
	}

	// 取文件后缀
    public static String getSuffixName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
	
	
}
