package com.thousandeyes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thousandeyes.exception.TweetException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;

/*
 * @desc: This is the service interface for the tweets requests
 * @author: Vineet Bhatkoti
 */


@Service
public interface TweetService {
	
	public List<Tweet> tweetList(User user, List<User> followerList, String searchParam)  throws TweetException;
}
