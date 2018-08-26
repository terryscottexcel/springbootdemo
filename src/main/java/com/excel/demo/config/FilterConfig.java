package com.excel.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excel.demo.filter.WordFilter;

@Configuration
public class FilterConfig {

	//这个@Bean注解相当于以前xml配置文件里的bean的定义，如下：
	//<bean id="theWordFilter" class="org.springframework.boot.web.servlet.FilterRegistrationBean"></bean>
	@Bean
	public FilterRegistrationBean<WordFilter> theWordFilter(){
		FilterRegistrationBean<WordFilter> filterBean = new FilterRegistrationBean<>(new WordFilter());
		filterBean.addUrlPatterns("/*");
		return filterBean;
	}
}
