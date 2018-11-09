package com.inspur.common.interceptor;


import com.inspur.common.util.RedJson;
import com.inspur.common.util.NotLoginException;
import com.inspur.common.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 加强版控制器
 * 
 * @author kongyongshun
 *
 */
@ControllerAdvice	// 可指定包前缀(basePackages = "com.zyd.blog.controller.admin")
public class TopControllerA {
	private static final Logger logger = LoggerFactory.getLogger(TopControllerA.class);
	// 全局异常拦截
	@ExceptionHandler
	public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("全局异常:" + e.getMessage());

		// 是否为ajax
		if(WebUtil.isAjax(request) == true){
			// ajax请求
			response.setContentType("application/json; charset=utf-8");	// http说明，我要返回JSON对象
			if(e instanceof NotLoginException){
				response.getWriter().print(RedJson.get(999, "未登录，请先登陆").toString());
				return;
			}
			response.getWriter().print(RedJson.get(500, e.getMessage().toString()));	// 普通异常输出：500 + 异常信息
		}else{
			// 非ajax请求
			if(e instanceof NotLoginException){
				response.sendRedirect("/dologin");	// 未登录权限转到登录页
				return;
			}
			throw e;
		}

	}
	
}
