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
	public /*synchronized*/ List<UserInfo> listUserInfo() {
		
		Integer dbmaxId = userMapper.selectMaxId();
		Integer maxId = (Integer) redisTemplate.opsForValue().get("maxIdVal");
		if(maxId==null) {
			maxId = 0;
		}
		boolean fromdb = false;
		if(dbmaxId > maxId) {
			fromdb = true;
		}
		
		//在高并发情况下，此处有些问题，比如10000人查询，每人刚进来，缓存肯定是没有数据的，就会查数据库，导致查10000次，效率很低
		//应该让第1个人查数据库，剩下的人就都可以从缓存里取了。
		//可以设置该方法是同步方法,加synchronized修饰即可。这样每次只有一个线程可以执行该方法，这样第二个线程进来时候就发现缓存有数据，从而不去数据库查了.
		//方法上加锁，会有牺牲效率，大部分线程都在等待，导致效率低下。另外的方法，则是在需要查数据库的地方进行加锁，这样效率会高一点
		
		//查询缓存
		List<UserInfo> alluserList = (List<UserInfo>) redisTemplate.opsForValue().get("allUserInfos");
		//缓存无数据才进入加锁的代码
		if(null == alluserList) {
			//锁一下自己的对象，可以让并发线程在执行到这里时等待一下
			synchronized(this) {
				//再查一次缓存，看是否有数据
				alluserList = (List<UserInfo>) redisTemplate.opsForValue().get("allUserInfos");
				
				//如果缓存里的数据为空，则查一次数据库
				if(null == alluserList || alluserList.isEmpty() || fromdb) {
					logger.info("从数据库读取");
					alluserList = userMapper.selectAll();
					//放入缓存中
					redisTemplate.opsForValue().set("allUserInfos",alluserList);
					redisTemplate.opsForValue().set("maxIdVal",dbmaxId);
				}
			}
		}
		
		return alluserList;
	}

	@Override
	public void insertUserInfo(UserInfo user) {
		if(user==null) {
			throw new RuntimeException("the user is null!");
		}
		userMapper.insert(user);
		
	}
	
	

}
