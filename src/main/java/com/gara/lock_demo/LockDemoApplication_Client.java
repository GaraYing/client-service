package com.gara.lock_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("com.gara.lock_demo.mapper") //扫描的是mapper.xml中namespace指向值的包位置
@SpringBootApplication
public class LockDemoApplication_Client {

	public static void main(String[] args) {
		SpringApplication.run(LockDemoApplication_Client.class, args);
	}
}
