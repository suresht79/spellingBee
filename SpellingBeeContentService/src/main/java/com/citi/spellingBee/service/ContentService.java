package com.citi.spellingBee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.spellingBee.repository.ContentRepository;

@Service
public class ContentService {
	
	@Autowired
	ContentRepository repository;

	public boolean saveWords(String word, String wordLevel, long userId) {
		boolean result = true;//service.saveWords(word, wordLevel, userId);
		repository.saveWords(word,  wordLevel, userId);
		return result;
	}
}
