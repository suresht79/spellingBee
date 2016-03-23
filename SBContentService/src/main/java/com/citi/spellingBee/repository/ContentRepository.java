package com.citi.spellingBee.repository;

import java.util.List;

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
		boolean result = true;
		jdbcTemplate.execute("insert into spellingbee.wordContent(Word, WordLevel, Status, UpdatedBy)" 
				+ " values('" + wordContent.getWord() +  "','" + wordContent.getWordLevel() + "'," + "'" + wordContent.getStatus().toString() + "'" + ",'" + wordContent.getUserId() + "')");
		return result;
	}
	
	public boolean updateWords(WordContent wordContent) {
		boolean result = true;
		jdbcTemplate.execute("update spellingBee.wordContent set "
				+ "Word='" + wordContent.getWord() + "',WordLevel='" + wordContent.getWordLevel() + "', Status='" + wordContent.getStatus().toString() + "'"
 						+ ", UpdatedBy='" + wordContent.getUserId() + "' where wordId=" + wordContent.getWordId());
		return result;
	}
	
	public List<WordContent> getWords(List statusList) {
		boolean result = true;
		String statusValues = StringUtils.join(statusList, "','");
		String sql = "select WordId, Word, WordLevel, Status, RejectedReason, UpdatedBy as userId from spellingbee.wordContent where Status in('" + statusValues + "')";
		List<WordContent> content  = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(WordContent.class));
		return content;
	}
}
