package com.example.common.util;

import java.io.Serializable;

/**
 * 业务Json的封装
 */
public class RedJson implements Serializable {

	private static final long serialVersionUID = 1L;	// 序列化版本号
	public static final int CODE_SUCCESS = 200;			// 成功状态码
	public static final int CODE_ERROR = 500;			// 失败状态码
	
	public int code; 	// 状态码
	public String msg; 	// 描述信息
	public Object data; // 携带对象
	
	
	

	/**
	 * 给msg赋值，连缀风格
	 */
	public RedJson setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * 给data赋值，连缀风格
	 */
	public RedJson setData(Object data) {
		this.data = data;
		return this;
	}

	/**
	 * 将data还原为指定类型并返回
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> cs) {
		return (T) data;
	}
	

	
	// ============================  构建  ================================== 
	
	
	public RedJson(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	// 返回成功
	public static RedJson getSuccess(String msg) {
		return new RedJson(CODE_SUCCESS, msg, null);
	}
	public static RedJson getSuccess(String msg, Object data) {
		return new RedJson(CODE_SUCCESS, msg, data);
	}
	
	// 返回失败
	public static RedJson getError(String msg) {
		return new RedJson(CODE_ERROR, msg, null);
	}
	
	// 返回一个自定义状态码的
	public static RedJson get(int code, String msg){
		return new RedJson(code, msg, null);
	}
	
	// 返回，根据受影响行数的(大于0=ok，小于0=error)
	public static RedJson getByLine(int line){
		if(line > 0){
			return getSuccess("ok");
		}
		return getError("error"); 
	}

	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{"
				+ "\"code\": " + code
				+ ", \"msg\": \"" + msg + "\""
				+ ", \"data\": " + data
				+ "}";
	}
	
	
	
	
	
}
