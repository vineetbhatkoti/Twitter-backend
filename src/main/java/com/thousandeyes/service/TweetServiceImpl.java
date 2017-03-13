package com.thousandeyes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thousandeyes.dao.TweetDAOImpl;
import com.thousandeyes.exception.DAOException;
import com.thousandeyes.exception.TweetException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;

@Service
public class TweetServiceImpl implements TweetService{
	
	@Autowired
	private TweetDAOImpl tweetDAO;
	
	@Override
	public List<Tweet> tweetList(User user, List<User> followerList, String searchParam)  throws TweetException
	{
		try
		{
			List<Tweet> tweetList = null;
			if(user != null)
			{
				tweetList = tweetDAO.tweetList(user,followerList,searchParam);
			}
			return tweetList;
		}
		catch(DAOException ex)
		{
			throw new TweetException("Failed to get the tweets of the User",ex);
		}
		
	}
}
