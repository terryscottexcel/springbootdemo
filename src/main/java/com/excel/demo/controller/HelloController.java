package com.excel.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@RequestMapping("/boot/hello")
	@ResponseBody
	public String sayHello() {
		return "Hello,Spring Boot";
	}
	
	@RequestMapping("/boot/hello/you")
	@ResponseBody
	public String sayHello(String username) {
		return "Hello,"+username;
	}
	
	@RequestMapping("/boot/hello/me")
	public String sayHello2() {
		return "hello";
	}
	
}
