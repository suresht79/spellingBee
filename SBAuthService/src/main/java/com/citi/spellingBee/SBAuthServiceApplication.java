package com.citi.spellingBee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SBAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBAuthServiceApplication.class, args);
	}
}
