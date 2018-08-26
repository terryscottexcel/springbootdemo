package com.excel.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.excel.dubbodemoapi.api.ProductService;
import com.excel.dubbodemoapi.domain.Product;
import com.excel.demo.mapper.ProductAnnoMapper;

@Service(interfaceClass = ProductService.class)
@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductAnnoMapper mapper;
	
	@Override
	public int mandaryProduct(Product product) {
		System.out.println("save or update or delete Product:"+product);
		int cnt = mapper.insertProduct(product);
		return cnt;
	}

	@Override
	public Product getProductByPk(Integer id) {
		System.out.println("getProduct by id="+id);
		Product product = new Product();
		product.setId(id);
		product.setCode("P"+id);
		product.setName("Product"+id);
		
		product = mapper.selectProduct(id);
		return product;
	}

	@Override
	public List<Product> getProductList() {
		System.out.println("getProductList all");
		List<Product> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Product product = new Product();
			int id = i+1;
			product.setId(id);
			product.setCode("P"+id);
			product.setName("Product"+id);
			
			list.add(product);
		}
		
		list = mapper.selectAllProduct();
		return list;
	}

}
