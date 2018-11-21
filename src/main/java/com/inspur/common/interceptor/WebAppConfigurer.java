package com.inspur.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截配置--调用链
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private SysInterceptor sysInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[] {"/login","/error/*","/admin/login","/*.html"};
        registry.addInterceptor(sysInterceptor).addPathPatterns("/**").
                excludePathPatterns(patterns);
        super.addInterceptors(registry);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("/login/login");
        registry.addViewController("/login").setViewName("/login/login");
        registry.addViewController("/error/404").setViewName("error/404");
        registry.addViewController("/error/500").setViewName("error/500");
        super.addViewControllers(registry);
    }
}