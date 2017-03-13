package com.thousandeyes.exception;

public class TweetException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5051929573827158704L;

	public TweetException(){
		super();
	}
	
	public TweetException(String message){
		super(message);
	}
	
	public TweetException(String message, Throwable cause){
		super(message, cause);
	}

}
