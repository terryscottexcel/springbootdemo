package com.excel.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.excel.demo.bean.User;
import com.excel.demo.bean.UserInfo;
import com.excel.demo.service.UserService;
import com.excel.demo.util.DateUtil;

@RestController
public class RESTFullController {
	@Autowired
	UserService userServiceImpl;
	
	
	@GetMapping("/boot/rest/listuser/byid/{uid}")
	public User listUser(@PathVariable(name="uid",required=false) Integer id) {
		User user = new User();
		user.setId(id);
		user.setName("张无忌");
		return user;
	}
	
	
	@GetMapping("/boot/rest/listuser/byname/{name}")
	public List<UserInfo> listUser2(@PathVariable(name="name",required=true) String name) {
		List<UserInfo> list = userServiceImpl.listUserInfoByName(name);
		
		System.out.println(list);
		return list;
	}
	
	@GetMapping("/boot/rest/listuser/{name}/byname")
	public List<UserInfo> listUser3(@PathVariable(name="name",required=false) String name) {
		List<UserInfo> list = userServiceImpl.listUserInfoByName(name);
		System.out.println(list);
		System.out.println(list.size());
		return list;
	}
	
	
	@GetMapping("/boot/rest/adduser/{uid}/{code}/{name}/{birthday}")
	public String listUser(@PathVariable(name="uid",required=false) Integer id,
			@PathVariable(name="code",required=false) String code,
			@PathVariable(name="name",required=false) String name,
			@PathVariable(name="birthday",required=false) String birthday
			) {
		UserInfo user = new UserInfo();
		user.setId(id);
		user.setUserCode(code);
		user.setUserName(name);
		user.setBirthday(DateUtil.convertToUtilDate(birthday));
		
		userServiceImpl.insertUserInfo(user);
		
		return "insert successfully";
	}
	
	
}
