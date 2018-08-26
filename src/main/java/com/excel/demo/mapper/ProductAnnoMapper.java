package com.excel.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.excel.dubbodemoapi.domain.Product;



public interface ProductAnnoMapper {
  @Results(value= {
		  @Result(column="rmk",property="desc"),
		  @Result(column="eff_date",property="effDate"),
		  @Result(column="clo_date",property="cloDate")
		  })
  @Select("SELECT * FROM t_product WHERE id = #{id}")
  Product selectProduct(int id);
  
  @Results(value= {
		  @Result(column="rmk",property="desc"),
		  @Result(column="eff_date",property="effDate"),
		  @Result(column="clo_date",property="cloDate")
		  })
  @Select("SELECT * FROM t_product")
  List<Product> selectAllProduct();
  
  @Insert("insert into t_product (code,name,rmk,eff_date,clo_date,validate) values (#{code},#{name},#{desc},#{effDate},#{cloDate},#{validate})")
  int insertProduct(Product blog);
  
  @Update("update t_product set code=#{code},name=#{name},rmk=#{desc},eff_date=#{effDate},clo_date=#{cloDate},validate=#{validate} where id=#{id}")
  int updateProduct(Product blog);
  
  @Delete("delete from t_product where id=#{id}")
  int deleteProduct(int id);
  
  @Select("select max(id) maxid from t_product")
  Integer selectMaxId();
  
  
}