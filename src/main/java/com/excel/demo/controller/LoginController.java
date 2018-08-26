package com.excel.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excel.demo.config.WebConfig;

@Controller
public class LoginController {
	@RequestMapping("/login/index")
	public String loginIndex() {
		return "login";
	}
	
	@RequestMapping("/login/login")
	public String login(String username,String userpassword,HttpSession session,Model model) {
		System.out.println("Into login");
		if("admin".equals(username) && "excel".equals(userpassword)) {
			session.setAttribute(WebConfig.LOGIN_USER_KEY, username);
			model.addAttribute("uname", username);
			model.addAttribute("msg", "you had login successfully!");
			return "loginSucc";
		}else {
			return "login";
		}
	}
}
