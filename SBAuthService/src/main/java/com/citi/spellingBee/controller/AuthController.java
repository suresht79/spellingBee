package com.citi.spellingBee.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	public static final Map<String, String> userMap = new HashMap<String, String>();
	static{
		
		userMap.put("wordsmith", "wordsmith_role");
		userMap.put("reviewer", "reviewer_role");
		userMap.put("pronouncer1", "pronouncer_role");
		userMap.put("pronouncer2", "pronouncer_role");
		userMap.put("contentadmin", "contentadmin_role");
	}

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public String authenticateUser(@RequestParam String userId) {
		String result = userMap.get(userId);
		return result;
	}
}
