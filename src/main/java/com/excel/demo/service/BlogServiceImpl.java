package com.excel.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excel.demo.bean.Blog;
import com.excel.demo.mapper.BlogXmlMapper;
/*
 * 注意：当加入@Service注解，但没有指定名字，在Controller类里面，声明的Service接口的变量名字必须是首字母小写的类的名字
 * 比如声明是：@Autowired BlogService blogServiceImpl，这样启动不会报错，会调用到实现类的方法
 * 如果声明是：@Autowired BlogService blogService,这样启动不报错，但运行调用到它时，就会报错，如下：
 * Invalid bound statement (not found): com.excel.demo.service.BlogService.queryBlogByParam
 * 如果声明是：@Autowired BlogService ss，这样启动会报错，错误是如下：
 * No qualifying bean of type 'com.excel.demo.service.BlogService' available: 
 * expected single matching bean but found 2: blogServiceImpl,blogService
 * 如果此处注解是@Service("bservice"),则在Controller类里，声明变量是：@Autowired BlogService bservice，这样就不会报错，运行也得出正确结果
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogXmlMapper mapper;
	
	@Override
	public List<Blog> queryBlogByParam(Map<String, Object> params) throws Exception {
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

	//一定要抛出异常，否则数据仍然会被更新掉,没有被回滚
	@Transactional
	@Override
	public void updateBlog() throws Exception {
		Blog blog = new Blog();
		blog.setId(6);
		blog.setCode("aaaa");
		blog.setName("dddd");
		blog.setDesc("cccc");
		
		mapper.updateBlog(blog);
		int a = 10/0;
		System.out.println(a);
		blog.setId(7);
		mapper.updateBlog(blog);
		
		
	}

	
}
