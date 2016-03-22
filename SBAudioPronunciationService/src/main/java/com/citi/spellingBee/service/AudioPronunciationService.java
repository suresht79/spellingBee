package com.citi.spellingBee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.spellingBee.domain.WordPronunciation;
import com.citi.spellingBee.repository.AudioPronunciationRepository;

@Service
public class AudioPronunciationService {
	
	@Autowired
	AudioPronunciationRepository pronunciationRepository;
	
	public boolean savePronunciation(WordPronunciation wordPronunciation) {
		boolean result = true;
		pronunciationRepository.savePronunciation(wordPronunciation);
		return result;
	}
	
	public WordPronunciation getPronunciation(Long wordId) {
		WordPronunciation wordPronunciation = pronunciationRepository.getPronunciation(wordId);
		return wordPronunciation;
	}
}
