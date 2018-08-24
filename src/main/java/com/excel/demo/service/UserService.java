package com.excel.demo.service;

import java.util.List;

import com.excel.demo.bean.UserInfo;

public interface UserService {
	public UserInfo listUserInfoById(Integer id);
	public void insertUserInfo(UserInfo user);
	public List<UserInfo> listUserInfoByName(String userName);
}
