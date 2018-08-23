package com.excel.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.demo.bean.Blog;
import com.excel.demo.mapper.BlogXmlMapper;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogXmlMapper mapper;
	
	@Override
	public List<Blog> queryBlogByParam(Map<String, Object> params) {
		List<Blog> list = null;
		try {
			if(params==null || params.isEmpty()) {
				list = mapper.selectAllBlog();
				return list;
			}
			
			Integer id = (Integer)params.get("blogId");
			
			if(id!=null) {
				list = new ArrayList<>();
				Blog blog = mapper.selectBlogById(id);
				list.add(blog);
				return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
