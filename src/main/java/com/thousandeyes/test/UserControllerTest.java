package com.thousandeyes.test;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	//Test case to check the people the user follows
	@Test
	public void getUserFollowsTest() throws Exception
	{
		mockMvc.perform(get("/user/follows").with(user("user").password("pass").roles("USER")).param("userId", "9"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	//Test case to get the follower of the users
	@Test
	public void getFollowersOfUserTest() throws Exception
	{
		mockMvc.perform(get("/user/followers").with(user("user").password("pass").roles("USER")).param("userId", "9"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	//Test case to check if the user is following other users
	@Test
	public void startFollowingTest() throws Exception
	{
		mockMvc.perform(post("/user/startFollowing").with(user("user").password("pass").roles("USER")).param("firstUserId", "9").param("secondUserId", "1"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	//Test case to check if the user can unfollow the other users
	@Test
	public void unfollowTest() throws Exception
	{
		mockMvc.perform(post("/user/unfollow").with(user("user").password("pass").roles("USER")).param("firstUserId", "1").param("secondUserId", "9"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	//Test case to get the most popular follower of all the users
	@Test
	public void mostPopularFollowerTest() throws Exception
	{
		mockMvc.perform(get("/user/mostPopularFollower").with(user("user").password("pass").roles("USER")))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
