package com.inspur.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * cookie工具类
 */
public class CookieUtil {


	/**
	 * 获取指定cookie
	 */
    public static Cookie getCookie(HttpServletRequest request,String cName) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie != null && cName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }


    /**
     * 添加cookie
     */
    public static void addCookie(HttpServletResponse response,String name,String value,String path,int timeout) {
        Cookie cookie = new Cookie(name, value);
        if(path == null) {
            path = "/";
        }
        cookie.setPath(path);
        cookie.setMaxAge(timeout);
        response.addCookie(cookie);
    }


    /**
     * 删除cookie
     */
    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookies != null && (name).equals(cookie.getName())) {
                addCookie(response,name,null,null,0);
                return;
            }
        }
    }


    /**
     * 修改cookie的value值
     */
    public static void updateCookie(HttpServletRequest request,HttpServletResponse response,String name,String value) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookies != null && (name).equals(cookie.getName())) {
                addCookie(response,name,value,cookie.getPath(),cookie.getMaxAge());
                return;
            }
        }
    }

    
    
}