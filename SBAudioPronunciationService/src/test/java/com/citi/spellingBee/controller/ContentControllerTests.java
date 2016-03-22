package com.citi.spellingBee.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citi.spellingBee.SBPronunciationServiceApplication;
import com.citi.spellingBee.service.AudioPronunciationService;

@SpringApplicationConfiguration(SBPronunciationServiceApplication.class)
@WebAppConfiguration
public class ContentControllerTests {

	
	private MockMvc mockMvc;

	@Autowired
	@Mock
	private AudioPronunciationService service;
	
	@InjectMocks
	@Autowired
	private AudioPronunciationController controller;
	
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
}
