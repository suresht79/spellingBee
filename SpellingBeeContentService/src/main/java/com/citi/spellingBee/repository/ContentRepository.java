package com.citi.spellingBee.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContentRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean saveWords(String word, String wordLevel, long userId) {
		boolean result = true;//service.saveWords(word, wordLevel, userId);
		//repository.saveWords(word,  wordLevel, userId);
		Map map = jdbcTemplate.queryForMap("select * from ContentMaster");
		return result;
	}
	
}
