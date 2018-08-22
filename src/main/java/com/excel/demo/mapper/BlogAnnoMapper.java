package com.excel.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.excel.demo.bean.Blog;

public interface BlogAnnoMapper {
  @Results(value= {
		  @Result(column="rmk",property="desc")
		  })
  @Select("SELECT * FROM t_blog WHERE id = #{id}")
  Blog selectBlog(int id);
  
  @Results(value= {
		  @Result(column="rmk",property="desc")
		  })
  @Select("SELECT * FROM t_blog")
  List<Blog> selectAllBlog();
  
  @Insert("insert into t_blog (code,name,rmk) values (#{code},#{name},#{desc})")
  int insertBlog(Blog blog);
  
  @Update("update t_blog set code=#{code},name=#{name},rmk=#{desc} where id=#{id}")
  int updateBlog(Blog blog);
  
  @Delete("delete from t_blog where id=#{id}")
  int deleteBlog(int id);
  
  @Select("select max(id) maxid from t_blog")
  Integer selectMaxId();
  
  
}