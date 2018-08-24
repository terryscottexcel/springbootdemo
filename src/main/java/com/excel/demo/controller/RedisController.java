package com.excel.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.demo.bean.UserInfo;
import com.excel.demo.mapper.UserInfoMapper;
import com.excel.demo.service.UserService;
import com.excel.demo.util.DateUtil;

@RestController
public class RedisController {

	private static final Logger logger = LoggerFactory.getLogger(MybatisXmlController.class);
	
	@Autowired
	UserInfoMapper userMapper;
	
	@Autowired
	UserService userServiceImpl;
	
	@RequestMapping("/boot/redis/adddemouser/{num}")
	public String insertUserDemoData(@PathVariable(name="num") Integer num) {
		String msg = "insert successfully";
		try {
			Integer id = userMapper.selectMaxId();
			logger.info("the max id = {}", id);
			if(id==null) {
				id=1;
			}
			for (int i = id; i < id + num; i++) {
				UserInfo blog = new UserInfo();
				blog.setUserCode("U" + i);
				blog.setUserName("UserInfo" + i);
				blog.setBirthday(DateUtil.convertToUtilDate("1985-08-06"));
				userMapper.insert(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "insert failed";
		}
		return msg;
	}
	
	
	@GetMapping("/boot/redis/listuser")
	public List<UserInfo> listUser() {
		List<UserInfo> list = userServiceImpl.listUserInfoByName("U");
		
		System.out.println(list);
		return list;
	}
}
