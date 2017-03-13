package com.thousandeyes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thousandeyes.exception.DAOException;
import com.thousandeyes.model.User;

@Repository
public interface UserDAO {
	public List<User> getFollowersOfUser(User usr) throws DAOException;
	
	public List<User> getUserFollows(User user) throws DAOException;
	
	public String startFollowing(User firstUser, User secondUser) throws DAOException;
	
	public String unfollow(User firstUser, User secondUser)  throws DAOException;
}
