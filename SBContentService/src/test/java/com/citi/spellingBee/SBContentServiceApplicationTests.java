package com.citi.spellingBee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SBContentServiceApplication.class)
@WebAppConfiguration
public class SBContentServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
