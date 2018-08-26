package com.excel.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns="/*")
public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("into WordFilter init() ...");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("进入EncodingFilter过滤器方法");
		System.out.println("调用EncodingFilter前的处理");
		chain.doFilter(request, response);
		System.out.println("调用EncodingFilter后的处理");
	}

	@Override
	public void destroy() {
		System.out.println("into WordFilter destroy() ...");

	}

}
