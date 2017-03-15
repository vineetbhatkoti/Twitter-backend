package com.thousandeyes.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void gettweetListTest() throws Exception
	{
		mockMvc.perform(get("/tweet/list").with(user("user").password("pass").roles("USER")).param("userId", "9"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	
	@Test
	public void tweetListWithSearchTest() throws Exception
	{
		mockMvc.perform(get("/tweet/list").with(user("user").password("pass").roles("USER")).param("userId", "9").param("search", "consectetuer"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
