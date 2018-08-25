package com.excel.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			logger.error(e.getMessage());
			msg = "insert failed";
		}
		return msg;
	}
	
	
	@GetMapping("/boot/redis/listuser")
	public List<UserInfo> listUser() {
		List<UserInfo> list = userServiceImpl.listUserInfo();
		
		
		return list;
	}
	
	@GetMapping("/boot/redis/listusertest")
	public List<UserInfo> listUserTest() {
		//创建线程对象，让线程里面执行查询数据。每个线程代表一个用户的请求
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				userServiceImpl.listUserInfo();
				long end = System.currentTimeMillis();
				logger.info("take times={}",(end-start));
			}
		};
		
		//多线程测试缓存穿透问题
		ExecutorService exeSrv = Executors.newFixedThreadPool(8);
		for (int i = 0; i < 100; i++) {
			exeSrv.submit(runnable);
		}
		
		
		List<UserInfo> list = userServiceImpl.listUserInfo();
		
		return list;
	}
}
