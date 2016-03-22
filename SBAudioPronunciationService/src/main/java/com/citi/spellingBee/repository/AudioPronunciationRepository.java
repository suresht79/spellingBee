package com.citi.spellingBee.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.spellingBee.domain.WordPronunciation;

@Repository
public class AudioPronunciationRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean savePronunciation(WordPronunciation wordPronunciation) {
		boolean result = true;
		jdbcTemplate.execute("insert into spellingbee.WordPronunciation(WordId, PronunciationAudio, CreatedBy)" 
				+ " values('" + wordPronunciation.getWordId() +  "','" + wordPronunciation.getPronunciationAudio() + "','"  +  wordPronunciation.getUserId() + "')");
		return result;
	}
	
	public WordPronunciation getPronunciation(Long wordId) {
		Map<String, Object> map = jdbcTemplate.queryForMap("select pronunciationId, WordId, pronunciationAudio, createdBy from spellingbee.WordPronunciation where wordId =" +  wordId);
		return new WordPronunciation();
	}
}
