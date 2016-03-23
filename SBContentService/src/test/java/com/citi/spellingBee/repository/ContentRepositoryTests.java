package com.citi.spellingBee.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.citi.spellingBee.SBContentServiceApplicationTests;
import com.citi.spellingBee.domain.WordContent;

@SpringApplicationConfiguration(SBContentServiceApplicationTests.class)
public class ContentRepositoryTests {

	@Mock
	@Autowired
	JdbcTemplate jdbcTemplate ;
			
	@Autowired
	@InjectMocks
	ContentRepository repository;
	
	@Before
	public void setUp() {
		this.repository = new ContentRepository();
		MockitoAnnotations.initMocks(this);
	}
	    
	@Test
	public void getWords(){
		Map<String,Object> results = new HashMap<String,Object>();
	    results.put("key1", "value1");

	    List<WordContent> listContent = new ArrayList<WordContent>();
		WordContent wordContent = new WordContent();
		wordContent.setUserId("wordsmith");
		wordContent.setWord("Word1");
		wordContent.setWordLevel("Easy");
		listContent.add(wordContent);
		
	    //when(jdbcTemplate.query(any(String.class),  any(RowMapper.class)).thenReturn(listContent));
	    
		//Assert.assertTrue(this.repository.saveWords(wordContent));
	}
	
}
