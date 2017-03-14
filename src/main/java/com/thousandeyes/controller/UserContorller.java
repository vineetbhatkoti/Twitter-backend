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

@RestController
@RequestMapping("/user")
public class UserContorller {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/followers",method = RequestMethod.GET, produces = "application/json")
    public List<User> getFollowersOfUser(@RequestParam(value="userId") int id) {
		User user = new User();
		user.setId(id);
		List<User> list =null;
		try {
			list = userService.getFollowersOfUser(user);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	@RequestMapping(value="/follows",method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserFollows(@RequestParam(value="userId") int id) {
		User user = new User();
		user.setId(id);
		List<User> list =null;
		try {
			list = userService.getUserFollows(user);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping(value="/startFollowing", method = RequestMethod.POST ,produces = "application/json")
    public ResponseMessage startFollowing(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
		User firstUser = new User();
		firstUser.setId(userId1);
		User secondUser = new User();
		secondUser.setId(userId2);
		String message =null;
		try {
			message = userService.startFollowing(firstUser,secondUser);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		ResponseMessage response = new ResponseMessage();
		response.setMessage(message);
		return response;
	}
	
	@RequestMapping(value="/unfollow",method= RequestMethod.POST, produces = "application/json")
    public ResponseMessage unfollow(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
		User firstUser = new User();
		firstUser.setId(userId1);
		User secondUser = new User();
		secondUser.setId(userId2);
		String message =null;
		try {
			message = userService.unfollow(firstUser,secondUser);
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		ResponseMessage response = new ResponseMessage();
		response.setMessage(message);
		return response;
	}

	
	@RequestMapping(value="/mostPopularFollower",method = RequestMethod.GET, produces = "application/json")
    public List<PopularUser> mostPopularFollower() {
		List<PopularUser> list =null;
		try {
			list = userService.mostPopularFollower();
		} catch (UserException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
}
