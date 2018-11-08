package com.example.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截配置--调用链
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

	@Bean
	public SysInterceptor sysInterceptor(){
		return new SysInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] patterns = new String[] {"/error/*","/admin/login","/*.html"};
		registry.addInterceptor(sysInterceptor()).addPathPatterns("/**").
				excludePathPatterns(patterns);
		super.addInterceptors(registry);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("/login/login");
		registry.addViewController("/error/404").setViewName("error/404");
		registry.addViewController("/error/500").setViewName("error/500");
		super.addViewControllers(registry);
	}
}