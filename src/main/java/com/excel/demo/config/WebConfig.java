package com.excel.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.excel.demo.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public static final String LOGIN_USER_KEY = "userName";
	
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		String[] addPathPatterns = {"/**"};
		String[] excludePathPatterns = {"/login/index","/login/login"};
		
		//注册一个登录验证的拦截器
		registry.addInterceptor(loginInterceptor).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
		
		//注册一个权限认证的拦截器
		//registry.addInterceptor(accessInterceptor).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
	}

	
}
