package com.bootcamp.springboot.bootcampdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootcampDemoApplication {

	public static void main(String[] args) {
//		System.setProperty("server.port", "8082");
		// or, use
		 System.getProperties().put("server.port", 8082);
		SpringApplication.run(BootcampDemoApplication.class, args);
	}

}
