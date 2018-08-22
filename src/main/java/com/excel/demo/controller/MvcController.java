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

//@RestController = @Controller+@ResponseBody
@RestController
public class MvcController {
	
	@RequestMapping("/boot/mvc")
	//@ResponseBody here not need add it
	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("张无忌");
		return user;
	}
	
	@RequestMapping(path="/boot/mvc/get",method=RequestMethod.GET)
	//@ResponseBody here not need add it
	public User getUser2() {
		User user = new User();
		user.setId(2);
		user.setName("洪七公");
		return user;
	}
	
	@RequestMapping(path="/boot/mvc/post",method=RequestMethod.POST)
	//@ResponseBody here not need add it
	public User getUser3() {
		User user = new User();
		user.setId(3);
		user.setName("郭靖");
		return user;
	}
	
	@GetMapping(path="/boot/mvc/get2")
	//@ResponseBody here not need add it
	public User getUser4() {
		User user = new User();
		user.setId(4);
		user.setName("黄蓉");
		return user;
	}
	
	@PostMapping(path="/boot/mvc/post2")
	//@ResponseBody here not need add it
	public User getUser5() {
		User user = new User();
		user.setId(5);
		user.setName("杨过");
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
