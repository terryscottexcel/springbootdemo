package com.excel.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.excel.demo.bean.UserInfo;
import com.excel.demo.controller.MybatisXmlController;
import com.excel.demo.mapper.UserInfoMapper;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserInfoMapper userMapper;
	
	//注入Springboot自动配置好的RedisTemplate模板
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	public UserInfo listUserInfoById(Integer id) {
		if(id==null) {
			return null;
		}
		UserInfo user = userMapper.selectByPrimaryKey(id);
		if(user!=null) {
			user.setUserName(user.getUserName()+"("+user.getUserCode()+")");
		}
		return user;
	}

	@Override
	public List<UserInfo> listUserInfoByName(String userName) {
		if(userName==null) {
			return  Collections.emptyList();
		}
		List<UserInfo> userListRst = null;
		Integer dbmaxId = userMapper.selectMaxId();
		Integer maxId = (Integer) redisTemplate.opsForValue().get("maxIdVal");
		if(maxId==null) {
			maxId = 0;
		}
		boolean fromdb = false;
		if(dbmaxId > maxId) {
			fromdb = true;
		}
		
		
		//查询缓存
		List<UserInfo> alluserList = (List<UserInfo>) redisTemplate.opsForValue().get("allUserInfos");
		//如果缓存里的数据为空，则查一次数据库
		if(null == alluserList || alluserList.isEmpty() || fromdb) {
			logger.info("从数据库读取");
			alluserList = userMapper.selectAll();
			//放入缓存中
			redisTemplate.opsForValue().set("allUserInfos",alluserList);
			redisTemplate.opsForValue().set("maxIdVal",dbmaxId);
		}
		
		//List<UserInfo> userList = userMapper.selectAll();
		List<UserInfo> userList = alluserList;
		if(userList!=null && !userList.isEmpty()) {
			userListRst = new ArrayList<>();
			for (UserInfo userInfo : userList) {
				if(userInfo.getUserName().contains(userName)) {
					userListRst .add(userInfo);
				}
			}
		}
		return userListRst;
	}

	@Override
	public void insertUserInfo(UserInfo user) {
		if(user==null) {
			throw new RuntimeException("the user is null!");
		}
		userMapper.insert(user);
		
	}
	
	

}
