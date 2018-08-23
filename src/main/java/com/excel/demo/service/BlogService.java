package com.excel.demo.service;

import java.util.List;
import java.util.Map;

import com.excel.demo.bean.Blog;

public interface BlogService {
	public List<Blog> queryBlogByParam(Map<String,Object> params) throws Exception;
	
	public void updateBlog() throws Exception;
	
}
