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

/*
 * @desc: This is the controller class for all tweet request REST API
 * @author: Vineet Bhatkoti
 */

@RestController
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@Autowired
	private TweetServiceImpl tweetService;

/*
 * 	@desc: This controller method is used to retrieve all the tweets of a user and all the tweets of the users they follow.
 *  @param: Integer userId, String search (optional param)
 *  @return: List<Tweet>  (list of all the tweets)
 */	
	@RequestMapping(value="/list",method = RequestMethod.GET, produces = "application/json")
    public List<Tweet> tweetList(@RequestParam(value="userId") int userId,@RequestParam(value="search",required=false) String searchParam) {
		User user = new User();
		user.setId(userId);
		List<Tweet> tweets = null;
		List<User> followerList =null;
		try {
			followerList = userService.getUserFollows(user);
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
