package com.excel.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

	@RequestMapping("/boot/jsp/hello")
	public String hello(Model model) {
		model.addAttribute("msg", "Hello,welcome to jsp page");
		return "hello";
	}
	
	@RequestMapping("/boot/jsp/listuser")
	public String list(Model model) {
		model.addAttribute("msg", "Hello,welcome to user data list page");
		return "listUser";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
