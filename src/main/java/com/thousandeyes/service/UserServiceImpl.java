package com.thousandeyes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thousandeyes.dao.UserDAOImpl;
import com.thousandeyes.exception.DAOException;
import com.thousandeyes.exception.UserException;
import com.thousandeyes.model.PopularUser;
import com.thousandeyes.model.User;


/*
 * @desc: This is the service implementation class for the user requests
 * @author: Vineet Bhatkoti
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOImpl userDAO;

	
/*
 * @desc: This service method is used to retrieve all the followers of the user
 * @param: User user
 * @return: List<User> 	
 */
	@Override
	public List<User> getFollowersOfUser(User usr) throws UserException {
		try
		{
			List<User> list = null;
			//check if the user is null
			if(usr != null)
			{
				//call to user DAO 
				list = userDAO.getFollowersOfUser(usr);
			}
			return list;
		}
		catch(DAOException ex)
		{
			throw new UserException("Failed to get the followers of the User",ex);
		}
	}


/*
 * @desc: This service method is used to retrieve all the people that the user follows
 * @param: User user
 * @return: List<User>
 */
	public List<User> getUserFollows(User user) throws UserException{
		try
		{
			List<User> list = null;
			//check if the user is null
			if(user != null)
			{
				//call to the user DAO
				list = userDAO.getUserFollows(user);
			}
			return list;
		}
		catch(DAOException ex)
		{
			throw new UserException("Failed to get the People whom user follows",ex);
		}
	}

	
/*
 * @desc: This service method is used to start following other users
 * @param: User firstUser, User secondUser
 * @return: String response for success or failure
 */	
	public String startFollowing(User firstUser, User secondUser)  throws UserException{
		try
		{
			String message = null;
			//check if the users are null
			if(firstUser != null && secondUser != null)
			{
				//call to user DAO
				message = userDAO.startFollowing(firstUser, secondUser);
			}
			return message;
		}
		catch(DAOException ex)
		{
			throw new UserException("Error while following user",ex);
		}
	}

	
/*
 * @desc: This service method is used to unfollow users
 * @param: User firstUser, User secondUser
 * @return: String response for success or failure
 */		
	public String unfollow(User firstUser, User secondUser) throws UserException {
		try
		{
			String message = null;
			//check if the user is null
			if(firstUser != null && secondUser != null)
			{
				//call to the user DAO
				message = userDAO.unfollow(firstUser, secondUser);
			}
			return message;
		}
		catch(DAOException ex)
		{
			throw new UserException("Error while unfollowing user",ex);
		}
	}

	
/*
 * @desc: This service method is used retrieve the most popular followers of the user
 * @param: None
 * @return: List<PopularUser> (List of popular users of the user)
 */		
	public List<PopularUser> mostPopularFollower() throws UserException{
		
		try
		{
			List<PopularUser> listUser= null;
			//call to the user DAO
			listUser = userDAO.mostPopularFollower();
			return listUser;
		}
		catch(DAOException ex)
		{
			throw new UserException("Error while unfollowing user",ex);
		}
	}
	

}
