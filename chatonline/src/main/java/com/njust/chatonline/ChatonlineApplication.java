package com.njust.chatonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.njust.chatonline.dao")
@ServletComponentScan//配置NettyListener 需加上此注解
public class ChatonlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatonlineApplication.class, args);
	}
}
