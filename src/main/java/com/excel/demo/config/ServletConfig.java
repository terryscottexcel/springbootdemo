package com.excel.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excel.demo.servlet.SayServlet;

@Configuration
public class ServletConfig {
	

	//这个@Bean注解相当于以前xml配置文件里的bean的定义，如下：
	//<bean id="theSayServlet" class="org.springframework.boot.web.servlet.ServletRegistrationBean"></bean>
	@Bean
	public ServletRegistrationBean<SayServlet> theSayServlet(){
		return new ServletRegistrationBean<>(new SayServlet(),"/boot/servlet/say");
	}
}
