package com.excel.demo.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.excel.demo.config.WebConfig;


@Component
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/**
	 * 进入controller层之前拦截请求
	 * 
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("---------------------开始进入请求地址拦截----------------------------");  
        HttpSession session = request.getSession();  
        if(!StringUtils.isEmpty(session.getAttribute(WebConfig.LOGIN_USER_KEY))){  
            return true;  
        }  
        else{  
            PrintWriter printWriter = response.getWriter();  
            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");  
            printWriter.write("<a href='/login/index'>To Login Page</a>");  
            return false;  
        }  

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("--------------处理请求完成后视图渲染之前的处理操作---------------"); 

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("---------------视图渲染之后的操作-------------------------"); 
	}

	
}
