package com.thousandeyes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thousandeyes.exception.UserException;
import com.thousandeyes.model.PopularUser;
import com.thousandeyes.model.ResponseMessage;
import com.thousandeyes.model.User;
import com.thousandeyes.service.UserServiceImpl;

/*
 * @desc: This is the controller class for all user request REST API
 * @author: Vineet Bhatkoti
 */

@RestController
@RequestMapping("/user")
public class UserContorller {
	@Autowired
	private UserServiceImpl userService;

	
/*
 * 	@desc: This controller method is used to retrieve all the followers of a user
 *  @param: Integer userId
 *  @return: List<User> (list of all the followers)
 */
	@RequestMapping(value="/followers",method = RequestMethod.GET, produces = "application/json")
    public List<User> getFollowersOfUser(@RequestParam(value="userId") int id) {
		
		//setting the user id in bean
		User user = new User();
		user.setId(id);
		List<User> list =null;
		
		try {
			//call to the service 
			list = userService.getFollowersOfUser(user);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
/*
 * @desc: This controller method is used to retrieve all the people the user follows  
 * @param: Integer userId
 * @return: List<User> (list of all the people the user follows)
 */
	
	
	@RequestMapping(value="/follows",method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserFollows(@RequestParam(value="userId") int id) {
		
		//setting the user id in the bean
		User user = new User();
		user.setId(id);
		List<User> list =null;
		
		try {
			//call to the service
			list = userService.getUserFollows(user);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
/*
 * @desc: This contoller method is used for a user to start following other users
 * @param: Integer firstUserId, Integer secondUserId
 * @return: Json message for success or failure	
 */
	@RequestMapping(value="/startFollowing", method = RequestMethod.POST ,produces = "application/json")
    public ResponseMessage startFollowing(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
		
		//setting user id in respective beans
		User firstUser = new User();
		firstUser.setId(userId1);
		User secondUser = new User();
		secondUser.setId(userId2);
		String message =null;
		
		try {
			//call to the service
			message = userService.startFollowing(firstUser,secondUser);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		//setting the string response in the response bean
		ResponseMessage response = new ResponseMessage();
		response.setMessage(message);
		return response;
	}
	
/*
 * @desc: This contoller method is used for a user to unfollow other users
 * @param: Integer firstUserId, Integer secondUserId
 * @return: Json message for success or failure	
 */	
	@RequestMapping(value="/unfollow",method= RequestMethod.POST, produces = "application/json")
    public ResponseMessage unfollow(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
		
		//setting user id's in the respective user bean
		User firstUser = new User();
		firstUser.setId(userId1);
		User secondUser = new User();
		secondUser.setId(userId2);
		String message =null;
		
		try {
			//call to the service 
			message = userService.unfollow(firstUser,secondUser);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		//setting the string response in the response bean
		ResponseMessage response = new ResponseMessage();
		response.setMessage(message);
		return response;
	}

/*
 * @desc: This controller is used to retrieve the list of most popular followers of a user
 * @param: none
 * @return: Json list of most popular followers	
 */
	@RequestMapping(value="/mostPopularFollower",method = RequestMethod.GET, produces = "application/json")
    public List<PopularUser> mostPopularFollower() {
		List<PopularUser> list =null;
		
		try {
			//call to the service
			list = userService.mostPopularFollower();
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
}
