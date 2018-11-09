package com.inspur.common.util;

/**
 * 没有登陆的异常
 */
public class NotLoginException extends RuntimeException {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 6806129545290130142L;

	/**
	 * 只允许以下几种异常
	 */
	public NotLoginException() {
        super("当前账号未登录");
    }
	

}
