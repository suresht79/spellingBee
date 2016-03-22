package com.citi.spellingBee.repository;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.spellingBee.domain.WordContent;

@Repository
public class ContentRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean saveWords(WordContent wordContent) {
		boolean result = true;//service.saveWords(word, wordLevel, userId);
		//Map<String, Object> map = jdbcTemplate.queryForMap("select * from country where code='ABW'");
		jdbcTemplate.execute("insert into spellingbee.wordContent(Word, WordLevel, Status, UpdatedBy)" 
				+ " values('" + wordContent.getWord() +  "','" + wordContent.getWordLevel() + "'," + "'" + wordContent.getStatus().toString() + "'" + ",'" + wordContent.getUserId() + "')");
		return result;
	}
	
	public boolean updateWords(WordContent wordContent) {
		boolean result = true;//service.saveWords(word, wordLevel, userId);
		//repository.saveWords(word,  wordLevel, userId);
		//Map<String, Object> map = jdbcTemplate.queryForMap("select userId, userName, role from User");
		
		jdbcTemplate.execute("update spellingBee.wordContent set "
				+ "Word='" + wordContent.getWord() + "',WordLevel='" + wordContent.getWordLevel() + "', Status='" + wordContent.getStatus().toString() + "'"
 						+ ", UpdatedBy='" + wordContent.getUserId() + "' where wordId=" + wordContent.getWordId());
		return result;
	}
	
	public List<WordContent> getWords(List statusList) {
		boolean result = true;//service.saveWords(word, wordLevel, userId);
		String statusValues = StringUtils.join(statusList, "','");
		//Map<String, Object> map = jdbcTemplate.queryForMap("select * from ContentMaster where userId=" + userId);
		//Map<String, Object> map = jdbcTemplate.queryForMap("select WordId, Word, WordLevel, Status, RejectedReason, UpdatedBy from ContentMaster where Status in('" + statusValues + "'");
		String sql = "select WordId, Word, WordLevel, Status, RejectedReason, UpdatedBy as userId from spellingbee.wordContent where Status in('" + statusValues + "')";
		List<WordContent> content  = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(WordContent.class));
		return content;
	}
}
