package com.thousandeyes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thousandeyes.exception.DAOException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;

/*
 * @desc: This is the DAO interface for the tweets requests
 * @author: Vineet Bhatkoti
 */

@Repository
public interface TweetDAO {
	public List<Tweet> tweetList(User user, List<User> followerList, String searchParam) throws DAOException;
}
