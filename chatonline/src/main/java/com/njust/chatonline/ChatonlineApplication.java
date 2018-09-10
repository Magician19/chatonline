package com.njust.chatonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.njust.chatonline.dao")
public class ChatonlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatonlineApplication.class, args);
	}
}
