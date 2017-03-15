package com.thousandeyes.model;

/*
 * @desc: This is model class for popular users
 * @author: Vineet Bhatkoti
 */
public class PopularUser {
	private int userId;
	private int mostPopularFollowerId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMostPopularFollowerId() {
		return mostPopularFollowerId;
	}
	public void setMostPopularFollowerId(int mostPopularFollowerId) {
		this.mostPopularFollowerId = mostPopularFollowerId;
	}
	
	
	
	
}
