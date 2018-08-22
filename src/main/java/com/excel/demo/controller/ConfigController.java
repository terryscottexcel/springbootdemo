package com.excel.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excel.demo.config.ConfigInfo;

@Controller
public class ConfigController {

	@Value("${boot.location}")
	private String location;
	
	@Value("${boot.company}")
	private String company;
	
	@RequestMapping("/boot/config")
	public @ResponseBody String getConfigInfo() {
		
		return "config by @Value:"+location+"--"+company;
	}
	
	
	@Autowired
	ConfigInfo cfgInfo;
	
	@RequestMapping("/boot/config2")
	public @ResponseBody String getConfigInfo2() {
		
		return "config by @ConfigurationProperties java :"+cfgInfo.getLocation()+"--"+cfgInfo.getCompany();
	}
}
