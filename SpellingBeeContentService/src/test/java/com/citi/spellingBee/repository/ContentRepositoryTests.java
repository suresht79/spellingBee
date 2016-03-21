package com.citi.spellingBee.repository;

import static org.mockito.Mockito.when;

import java.util.HashMap;
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

import com.citi.spellingBee.SpellingBeeContentServiceApplicationTests;

@SpringApplicationConfiguration(SpellingBeeContentServiceApplicationTests.class)
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
	public void saveWords(){
		Map<String,Object> results = new HashMap<String,Object>();
	    results.put("key1", "value1");

	    when(jdbcTemplate.queryForMap(org.mockito.Matchers.any(String.class))).thenReturn(results);
		Assert.assertTrue(this.repository.saveWords("test","easy",1));
	}
	
}
