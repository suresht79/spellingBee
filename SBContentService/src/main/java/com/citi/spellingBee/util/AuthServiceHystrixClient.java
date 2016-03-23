package com.citi.spellingBee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class AuthServiceHystrixClient {
	
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;
	
	String AUTH_SERVICE_ENDPOINT="http://AUTH-APP/authenticateUser";
	
	@HystrixCommand(fallbackMethod = "getRoleFallback")
	public String getRole(String userId) {
		return restTemplate.getForObject(AUTH_SERVICE_ENDPOINT + "?userId=" + userId, String.class);
	}
	
	public String getRoleFallback(String userId) {
		return "reviewer_role";
	}
}
