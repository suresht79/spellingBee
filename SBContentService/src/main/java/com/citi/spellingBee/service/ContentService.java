package com.citi.spellingBee.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.domain.WordPronunciation;
import com.citi.spellingBee.repository.ContentRepository;
import com.citi.spellingBee.util.AuthServiceHystrixClient;
import com.citi.spellingBee.util.ContentStatus;
import com.citi.spellingBee.util.UserNotValidException;

@Service
public class ContentService {
	
	@Autowired
	ContentRepository contentRepository;
	
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;

	@Autowired
	private AuthServiceHystrixClient authServiceHystrixClient;
	
	String PRONUNCIATION_SERVICE_ENDPOINT="http://PRONUNCIATION-APP/savePronunciation";
	
	public boolean saveWords(WordContent wordContent) {
		boolean result = true;//

	    String role = authServiceHystrixClient.getRole(wordContent.getUserId());
		switch(role){
			case "wordsmith_role": {
				if(wordContent.getWordId() == null){
					wordContent.setStatus(ContentStatus.NEW.toString());
					contentRepository.saveWords(wordContent);
				}
				else{
					wordContent.setStatus(ContentStatus.WORD_CORRECTED_BY_WORDSMITH.toString());
					contentRepository.updateWords(wordContent);
				}
				break;
			}
			case "reviewer_role":{
				//reject wordsmith words with reason.
				//reject pronunciation.
				boolean reviewerApproved=true;
				int phase=1;
				if(phase==1){
					if(reviewerApproved){
						wordContent.setStatus(ContentStatus.WORD_APPROVED_BY_REVIEWER.toString());
						contentRepository.updateWords(wordContent);
						//repository.updateWords(wordId, word,  wordLevel, userId, ContentStatus.WORD_APPROVED_BY_REVIEWER );
					}
					else{
						wordContent.setStatus(ContentStatus.WORD_REJECTED_BY_REVIEWER.toString());
						contentRepository.updateWords(wordContent);
						//repository.updateWords(wordId, word,  wordLevel, userId, ContentStatus.WORD_REJECTED_BY_REVIEWER, rejectedReason);
					}
				}
				else if(phase==2){
					if(reviewerApproved){
						wordContent.setStatus(ContentStatus.PRONUNCIATION_APPROVED_BY_REVIEWER.toString());
						contentRepository.updateWords(wordContent);
						//repository.updateWords(wordId, word,  wordLevel, userId, ContentStatus.WORD_APPROVED_BY_REVIEWER );
					}
					else{
						wordContent.setStatus(ContentStatus.PRONUNCIATION_REJECTED_BY_REVIEWER.toString());
						contentRepository.updateWords(wordContent);
						//repository.updateWords(wordId, word,  wordLevel, userId, ContentStatus.WORD_REJECTED_BY_REVIEWER, rejectedReason);
					}
				}
				break;
			}
			case "pronouncer_role":{
				//Call PronunciationService to save Audio
				wordContent.setStatus(ContentStatus.PRONUNCIATION_RECORDED.toString());
				contentRepository.updateWords(wordContent);
				WordPronunciation wordPronunciation = new WordPronunciation();
				wordPronunciation.setWordId(wordContent.getWordId());
				wordPronunciation.setUserId(wordContent.getUserId());
				wordPronunciation.setPronunciationAudio(wordContent.getPronunciationAudio());
				
			    saveWordPronunciation(wordPronunciation);
			 
				break;
			}
		}
		return result;
	}

	private Boolean saveWordPronunciation(WordPronunciation wordPronunciation) {
		return restTemplate.postForObject( PRONUNCIATION_SERVICE_ENDPOINT, wordPronunciation, Boolean.class);
	}

	/*@HystrixCommand(fallbackMethod = "getRole")
	public String getRole(String userId) {
		return restTemplate.getForObject(AUTH_SERVICE_ENDPOINT + "?userId=" + userId, String.class);
	}
	
	public String getRole() {
		return "reviewer_role";
	}*/
	
	public List<WordContent> getWords(String userId) throws UserNotValidException{
		boolean result = true;
		
		if(StringUtils.isEmpty(userId)){
			throw new UserNotValidException();
		}
		List<WordContent> wordContentList = null;
		String role = authServiceHystrixClient.getRole(userId);
		switch(role){
			case "wordsmith_role": {
				List<String> status = new ArrayList<String>();
				status.add(ContentStatus.WORD_REJECTED_BY_REVIEWER.toString());
				wordContentList = contentRepository.getWords(status);
				break;
			}
			case "reviewer_role":{
				//reject wordsmith words with reason.
				//reject pronunciation.
				int phase=1;
				if(phase==1){
					List<String> status = new ArrayList<String>();
					status.add(ContentStatus.NEW.toString());
					status.add(ContentStatus.WORD_CORRECTED_BY_WORDSMITH.toString());
					wordContentList = contentRepository.getWords(status );
				}
				else if(phase==2){
					List<String> status = new ArrayList<String>();
					status.add(ContentStatus.PRONUNCIATION_RECORDED.toString());
					wordContentList = contentRepository.getWords(status );
				}
				break;
			}
			case "pronouncer_role":{
				//Call PronunciationService to save Audio
				List<String> status = new ArrayList<String>();
				status.add(ContentStatus.WORD_APPROVED_BY_REVIEWER.toString());
				status.add(ContentStatus.PRONUNCIATION_REJECTED_BY_REVIEWER.toString());
				wordContentList = contentRepository.getWords(status );
				break;
			}
			
		}
		return wordContentList;
	}
}
