package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class DemoApplication {
	/*
	SET REFERENTIAL_INTEGRITY FALSE;
	delete from Models
TRUNCATE TABLE Models restart identity;
SET REFERENTIAL_INTEGRITY TRUE;
	 */

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



}
