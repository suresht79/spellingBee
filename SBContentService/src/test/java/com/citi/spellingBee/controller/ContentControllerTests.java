package com.citi.spellingBee.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citi.spellingBee.SBContentServiceApplication;
import com.citi.spellingBee.domain.WordContent;
import com.citi.spellingBee.service.ContentService;
import com.citi.spellingBee.util.UserNotValidException;

@SpringApplicationConfiguration(SBContentServiceApplication.class)
@WebAppConfiguration
public class ContentControllerTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private MockMvc mockMvc;

	@Autowired
	@Mock
	private ContentService service;
	
	@InjectMocks
	@Autowired
	private ContentController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
	}
	
	@Test
    public void saveWordsTest() throws Exception {
		/*when(this.service.saveWords(any(String.class), any(String.class), any(Long.class)))
		.thenReturn(true);*/
        MockHttpServletRequestBuilder request = get("/saveWords");
        request.param("word", "Suresh");
		request.param("wordLevel", "easy");
		request.param("userId", "1");
		
        mockMvc.perform(request)
                .andExpect(status().isOk())
        .andExpect(content().string("true"));
        
        System.out.println("test done");
    }
	
	@Test
    public void getWordsTest() throws Exception {
		List<WordContent> listContent = new ArrayList<WordContent>();
		WordContent wordContent = new WordContent();
		wordContent.setUserId("wordsmith");
		wordContent.setWord("Word1");
		wordContent.setWordLevel("Easy");
		listContent.add(wordContent);
		
		when(this.service.getWords(any(String.class)))
		.thenReturn(listContent);
		
        MockHttpServletRequestBuilder request = get("/getWords");
		request.param("userId", "wordsmith");
		
		ResultActions result = mockMvc.perform(request)
				.andExpect(status().isOk())
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        		//.andExpect(jsonPath("$", hasSize(1)));
                //.andExpect(content().string("true"));
        
		//check for invalid username scenario exception as a negative case
		verify(service, times(1)).getWords(any(String.class));
		
		
		/*request = get("/getWords");
		request.param("userId", "");
		try{
			result = mockMvc.perform(request)
					.andExpect(status().is5xxServerError());
		}
		catch(Exception ex){
			assertThat(ex,is(instanceOf(UserNotValidException.class)));
		}*/
		
        System.out.println("test done");
    }
}
