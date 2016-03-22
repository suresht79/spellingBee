package com.citi.spellingBee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.service.ContentService;

@RestController
public class ContentController {

	@Autowired
	ContentService service;

	@RequestMapping(value = "/saveWords", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Boolean saveWords(@RequestBody WordContent wordContent) {
		boolean result = service.saveWords(wordContent);
		return result;
	}
	
	@RequestMapping(value = "/getWords", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<WordContent> getWords(@RequestParam(name = "userId") String userId) {
		List<WordContent> wordContent = service.getWords(userId);
		return wordContent;
	}
}
