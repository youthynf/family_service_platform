package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class FamilyServicePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyServicePlatformApplication.class, args);
	}

}
