package com.inspur.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * 权限验证工具类
 */
public class JurUtil {

	
	public static String tokenName = "inspur_token";		// cookie(token)的名字
	public static int tokenTime = 30 * 24 * 60 * 60;		// cookie失效时间，单位s 此为30天
	public static boolean isGetToken = false;		// 是否在无cookie时尝试从get参数中获取token


	/**
	 * 在指定客户端登陆上指定userId
	 */
	public static String setUid(HttpServletResponse response, String uid,String username){
		String tokenValue = RedisUtil.get(tokenName+uid);
		if(tokenValue == null){
			tokenValue =  JwtUtils.createJWT(UUID.randomUUID().toString().replace("-", ""),username,tokenTime);
		}
		// System.out.println(uid + "生成的tokenValue："+tokenValue);
		CookieUtil.addCookie(response, tokenName, tokenValue, "/", tokenTime);	// 客户端注入cookie
		RedisUtil.set(tokenValue, uid);	 // redis上注入键值 token->uid
		RedisUtil.set(tokenName + uid, tokenValue);	// redis存储上反键值 uid->token
		return tokenValue;
	}

	/**
	 * 在指定客户端清除userId状态,清楚cookie和redis缓存
	 */
	public static void removeUid(HttpServletRequest request, HttpServletResponse response){
		Cookie cookie = CookieUtil.getCookie(request, tokenName);
		if(cookie == null){
			return;
		}
		String tokenValue = cookie.getValue();
		String uid = getUid(request);
		CookieUtil.delCookie(request,response, tokenName);	// 清除cookie
		RedisUtil.del(tokenValue);	// 清除redis缓存 token->uid
		RedisUtil.del(tokenName + uid);		// 清除redis缓存 uid->token
	}

	// 清除指定uid的redis缓存
	public static void removeUid(long uid){
		String tokenValue = RedisUtil.get(tokenName + String.valueOf(uid));
		RedisUtil.del(tokenValue);		// 清除redis缓存 token->uid
		RedisUtil.del(tokenName + String.valueOf(uid));		// 清除redis缓存 uid->token
	}


	// ===================  拿值  =================

	// 返回当前用户的token,没有返回null
	public static String getToken(HttpServletRequest request){
		Cookie cookie = CookieUtil.getCookie(request, tokenName);
		if(cookie == null){
			return null;
		}
		return cookie.getValue();
	}

	/**
	 * 返回当前userId，未登录返回0，
	 * @return
	 */
	public static String getUid(HttpServletRequest request){
		try {
			Cookie cookie = CookieUtil.getCookie(request, tokenName);
			if(cookie == null){
				String tokenValue = request.getParameter(tokenName);
				if(isGetToken == true && tokenValue != null){
					cookie = new Cookie(tokenName, tokenValue);	// 伪造cookie
				}else{
					return null;
				}
			}
			String tokenValue = cookie.getValue();
			String uidString = RedisUtil.get(tokenValue);
			return (uidString == null ? "" :  uidString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 返回当前userId，未登录返直接炸
	 * @return
	 */
	public static String getUidThrow(HttpServletRequest request){
		String uid = getUid(request);
		if(uid == null){
			throw new NotLoginException();
		}
		return uid;
	}
}
