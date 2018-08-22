package com.excel.demo.bean;

import java.io.Serializable;

public class Blog implements Serializable{
	private Integer id;
	private String code;
	private String name;
	private String desc;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "Blog [id=" + id + ", code=" + code + ", name=" + name + ", desc=" + desc + "]";
	}
	
	
	
	
	
}
