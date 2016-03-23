package com.citi.spellingBee.service;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.repository.ContentRepository;
import com.citi.spellingBee.util.UserNotValidException;

@RunWith(BlockJUnit4ClassRunner.class)
public class ContentServiceTests {

@Autowired
@InjectMocks
private ContentService service;

@Mock
@Autowired
RestTemplate restTemplate;

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
		WordContent wordContent = new WordContent();
		wordContent.setUserId("wordsmith");
		wordContent.setWord("Word1");
		wordContent.setWordLevel("Easy");
		
		when(restTemplate.getForObject(
				  Matchers.anyString(), Matchers.eq(String.class))
				).thenReturn("wordsmith_role");
		
		when(this.repository.saveWords(any(WordContent.class)))
		.thenReturn(true);
		assertTrue(this.service.saveWords(wordContent));
	}
	
	@Test
    public void getWordsTest() throws Exception {
		List<WordContent> listContent = new ArrayList<WordContent>();
		
		WordContent wordContent = new WordContent();
		wordContent.setUserId("wordsmith");
		wordContent.setWord("Word1");
		wordContent.setWordLevel("Easy");
		listContent.add(wordContent);
		        
		when(restTemplate.getForObject(
				  Matchers.anyString(), Matchers.eq(String.class))
				).thenReturn("wordsmith_role");
		
		when(this.repository.getWords(any(List.class)))
		.thenReturn(listContent);
		
		List<WordContent> listContent1 = this.service.getWords("wordsmith");
		
		verify(repository, times(1)).getWords(any(List.class));
		
    }
	
	@Test
    public void getWordsForInvalidUserTest() throws Exception {
		//Test for handling exceptions
		try{
			List<WordContent> listContent2 = this.service.getWords("");
		}
		catch(UserNotValidException ex){
			assertThat(ex,is(instanceOf(UserNotValidException.class)));
		}
	}
}
