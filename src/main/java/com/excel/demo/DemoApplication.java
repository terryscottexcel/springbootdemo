package com.excel.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@ComponentScan("com.excel.demo")
@MapperScan("com.excel.demo")
@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		logger.info("SpringBoot容器开始启动。。。");
		SpringApplication.run(DemoApplication.class, args);
		logger.info("SpringBoot容器完成启动。。。");
	}
	
	
	

}
