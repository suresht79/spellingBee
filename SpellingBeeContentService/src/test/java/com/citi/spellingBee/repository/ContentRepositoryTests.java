package com.citi.spellingBee.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

@RunWith(BlockJUnit4ClassRunner.class)
public class ContentRepositoryTests {

	
	@InjectMocks
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	
			
	@Autowired
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
	    /*when(this.service.saveWords(any(String.class), any(String.class), any(Long.class)))
		.thenReturn(true);*/

	    //when(jdbcTemplate.queryForMap(any(String.class))).thenReturn(results);
	    //when(jdbcTemplate.execute(any(String.class))).thenReturn(results);
		Assert.assertTrue(this.repository.saveWords("test","easy",1));
	}
	
}
