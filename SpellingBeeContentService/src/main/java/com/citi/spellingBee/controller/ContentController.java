package com.citi.spellingBee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.spellingBee.service.ContentService;

@RestController
public class ContentController {

	@Autowired
	ContentService service;

	@RequestMapping(value = "/saveWords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveWords(@RequestParam(name = "word") String word,
			@RequestParam(name = "wordLevel") String wordLevel,
			@RequestParam(name = "userId") long userId) {
		
		boolean result = service.saveWords(word, wordLevel, userId);

		return result;

	}
}
