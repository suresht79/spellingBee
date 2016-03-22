package com.citi.spellingBee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.domain.WordPronunciation;
import com.citi.spellingBee.service.AudioPronunciationService;

@RestController
public class AudioPronunciationController {

	@Autowired
	AudioPronunciationService service;

	@RequestMapping(value = "/savePronunciation", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Boolean savePronunciation(@RequestBody WordPronunciation wordPronunciation) {
		boolean result = service.savePronunciation(wordPronunciation);
		return result;
	}
	
	@RequestMapping(value = "/getPronunciation", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public WordPronunciation getPronunciation(@RequestParam(name = "word") Long wordId ) {
		WordPronunciation wordPronunciation = service.getPronunciation(wordId);
		return wordPronunciation;
	}
}
