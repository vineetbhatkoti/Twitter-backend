package com.thousandeyes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thousandeyes.exception.DAOException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;


@Repository
public interface TweetDAO {
	public List<Tweet> tweetList(User user, List<User> followerList, String searchParam) throws DAOException;
}
