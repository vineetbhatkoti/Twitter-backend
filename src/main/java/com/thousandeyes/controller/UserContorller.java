package com.thousandeyes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thousandeyes.exception.UserException;
import com.thousandeyes.model.User;
import com.thousandeyes.service.UserServiceImpl;

@RestController
public class UserContorller {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/userFollowers")
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
	
	
	
	@RequestMapping("/userFollows")
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
	
	@RequestMapping("/startFollowing")
    public String startFollowing(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
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
		
		return message;
	}
	
	@RequestMapping("/unfollow")
    public String unfollow(@RequestParam(value="firstUserId") int userId1,@RequestParam(value="secondUserId") int userId2) {
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
		
		return message;
	}

}
