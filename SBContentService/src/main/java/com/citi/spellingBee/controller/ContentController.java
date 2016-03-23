package com.citi.spellingBee.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.service.ContentService;
import com.citi.spellingBee.util.UserNotValidException;

@RestController
public class ContentController {

	@Autowired
	ContentService service;

	@RequestMapping(value = "/saveWords", method = RequestMethod.POST)
	public Boolean saveWords(@RequestBody WordContent wordContent) {
		boolean result = service.saveWords(wordContent);
		return result;
	}
	
	@RequestMapping(value = "/getWords", method = RequestMethod.GET)
	public List<WordContent> getWords(@RequestParam(name = "userId") String userId) throws UserNotValidException{
		List<WordContent> wordContent = service.getWords(userId);
		return wordContent;
	}
}
