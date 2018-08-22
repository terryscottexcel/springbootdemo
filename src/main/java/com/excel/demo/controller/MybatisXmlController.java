package com.excel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.demo.bean.Blog;
import com.excel.demo.bean.UserInfo;
import com.excel.demo.mapper.BlogXmlMapper;
import com.excel.demo.mapper.UserInfoMapper;

@RestController
public class MybatisXmlController {

	@Autowired
	private BlogXmlMapper mapper;
	
	@Autowired
	UserInfoMapper userMapper;

	@RequestMapping("/boot/listblog")
	public List<Blog> listAllBlog() {
		List<Blog> list = null;
		try {
			list = mapper.selectAllBlog();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/boot/demoblog")
	public String insertBlogDemoData() {
		String msg = "insert successfully";
		try {
			Integer id = mapper.selectMaxId();
			System.out.println("the max id = " + id);
			for (int i = id; i < id + 100; i++) {
				Blog blog = new Blog();
				blog.setCode("B" + i);
				blog.setName("Blog" + i);
				blog.setDesc("the Blog Description for Blog" + i);
				mapper.insertBlog(blog);
			}
			List<Blog> list = null;
			list = mapper.selectAllBlog();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "insert failed";
		}
		return msg;
	}

	// url will be : http://localhost:8082/boot/listblog/39
	// the {bid} mapping the url 39
	@RequestMapping("/boot/listblog/{bid}")
	public Blog listBlogById(@PathVariable(name = "bid") Integer id) {
		Blog blog = null;
		try {
			blog = mapper.selectBlogById(id);
			System.out.println(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blog;
	}

	// url will be : http://localhost:8082/boot/delblog/39
	@RequestMapping("/boot/delblog/{bid}")
	public String deleteBlogById(@PathVariable(name = "bid") Integer id) {
		String msg = "delete successfully";
		try {
			int cnt = mapper.deleteBlog(id);
			System.out.println(cnt+" records are "+msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "delete failed";
		}
		return msg;
	}
	
	@RequestMapping("/boot/listuser")
	public List<UserInfo> listAllUser() {
		List<UserInfo> list = null;
		try {
			list = userMapper.selectAll();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
