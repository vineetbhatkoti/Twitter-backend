package com.thousandeyes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thousandeyes.exception.TweetException;
import com.thousandeyes.exception.UserException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;
import com.thousandeyes.service.TweetServiceImpl;
import com.thousandeyes.service.UserServiceImpl;

@RestController
public class TweetController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@Autowired
	private TweetServiceImpl tweetService;
	
	@RequestMapping(value="/tweetList",method = RequestMethod.GET, produces = "application/json")
    public List<Tweet> tweetList(@RequestParam(value="search") String searchParam) {
		
		///remove this Use it from auth 
		int id = 9;
		User user = new User();
		user.setId(id);
		List<Tweet> tweets = null;
		List<User> followerList =null;
		try {
			followerList = userService.getFollowersOfUser(user);
		} catch (UserException e) {
			e.printStackTrace();
		}
		
		try
		{
			tweets = tweetService.tweetList(user, followerList, searchParam);
		}
		catch(TweetException e)
		{
			e.printStackTrace();
		}
		
		
		return tweets;
	}
}
