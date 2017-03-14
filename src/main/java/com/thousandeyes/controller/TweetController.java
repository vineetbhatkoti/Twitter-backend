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
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@Autowired
	private TweetServiceImpl tweetService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET, produces = "application/json")
    public List<Tweet> tweetList(@RequestParam(value="userId") int userId,@RequestParam(value="search",required=false) String searchParam) {
		User user = new User();
		user.setId(userId);
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
