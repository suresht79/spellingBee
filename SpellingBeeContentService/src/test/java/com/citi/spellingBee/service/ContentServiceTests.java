package com.citi.spellingBee.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citi.spellingBee.repository.ContentRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class ContentServiceTests {

@Autowired
@InjectMocks
private ContentService service;

@Mock
@Autowired
private ContentRepository repository;
	
	@Before
	public void setUp() {
		this.service = new ContentService();
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void saveWords(){
		when(this.repository.saveWords(any(String.class), any(String.class), any(Long.class)))
		.thenReturn(true);
		Assert.assertTrue(this.service.saveWords("test","easy",1));
	}
	
}
