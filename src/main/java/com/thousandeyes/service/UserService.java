package com.thousandeyes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thousandeyes.exception.UserException;
import com.thousandeyes.model.PopularUser;
import com.thousandeyes.model.User;

@Service
public interface UserService {
	public List<User> getFollowersOfUser(User usr) throws UserException;
	
	public List<User> getUserFollows(User user) throws UserException;
	
	public String startFollowing(User firstUser, User secondUser)  throws UserException;
	
	public String unfollow(User firstUser, User secondUser) throws UserException;
	
	public List<PopularUser> mostPopularFollower() throws UserException;
}
