package com.excel.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.excel.demo.util.DateUtil;
import com.excel.dubbodemoapi.api.ProductService;
import com.excel.dubbodemoapi.domain.Product;

@Controller
public class DubboController {
	// 在这里指定使用协议连接提供者 获取服务
	@Reference(url = "dubbo://127.0.0.1:20880")
	private ProductService productServiceImpl;

	@RequestMapping("/boot/dubbo/product/{pid}")
	@ResponseBody
	public Product getProduct(@PathVariable(name = "pid") Integer id) {
		Product product = productServiceImpl.getProductByPk(id);
		System.out.println(product);
		
		return product;
	}
	
	@RequestMapping("/boot/dubbo/product/add")
	@ResponseBody
	public String addProduct() {
		for (int i = 0; i < 100; i++) {
			int id=i+1;
			Product product = new Product();
			product.setId(id);
			product.setCode("P"+id);
			product.setName("Product"+id);
			product.setEffDate(DateUtil.convertToUtilDate("2018/08/19"));
			product.setValidate(true);
			int cnt = productServiceImpl.mandaryProduct(product);
			System.out.println(product);
			System.out.println(cnt);
		}
		
		return "add success";
	}
	
	@RequestMapping("/boot/dubbo/product/all")
	@ResponseBody
	public List<Product> listProductList() {
		List<Product> list = productServiceImpl.getProductList();
		System.out.println(list);
		
		return list;
	}
	
}
