package com.excel.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.excel.demo.bean.User;
import com.excel.demo.util.DateUtil;

//@RestController = @Controller+@ResponseBody
@RestController
public class MvcController {
	
	@RequestMapping(path="/boot/mvc")
	//@ResponseBody here not need add it
	@ResponseBody
	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("张无忌");
		user.setBirthday(DateUtil.convertToUtilDate("1985/06/19"));
		return user;
	}
	
	@RequestMapping(path="/boot/mvc/get",method=RequestMethod.GET)
	//@ResponseBody here not need add it
	public User getUser2() {
		User user = new User();
		user.setId(2);
		user.setName("洪七公");
		user.setBirthday(DateUtil.convertToUtilDate("1919/08/26"));
		return user;
	}
	
	@RequestMapping(path="/boot/mvc/post",method=RequestMethod.POST)
	//@ResponseBody here not need add it
	public User getUser3() {
		User user = new User();
		user.setId(3);
		user.setName("郭靖");
		user.setBirthday(DateUtil.convertToUtilDate("1926/09/18"));
		return user;
	}
	
	@GetMapping(path="/boot/mvc/get2")
	//@ResponseBody here not need add it
	public User getUser4() {
		User user = new User();
		user.setId(4);
		user.setName("黄蓉");
		user.setBirthday(DateUtil.convertToUtilDate("1926/12/27"));
		return user;
	}
	
	@PostMapping(path="/boot/mvc/post2")
	//@ResponseBody here not need add it
	public User getUser5() {
		User user = new User();
		user.setId(5);
		user.setName("杨过");
		user.setBirthday(DateUtil.convertToUtilDate("1935/06/29"));
		return user;
	}
	
	@DeleteMapping(path="/boot/mvc/del")
	//@ResponseBody here not need add it
	public String delete() {
		
		return "Delete successfully";
	}
	
	@PutMapping(path="/boot/mvc/put")
	//@ResponseBody here not need add it
	public String put() {
		
		return "Put successfully";
	}
}
