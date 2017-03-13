package com.thousandeyes.exception;

public class UserException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -322499620769840638L;

	public UserException(){
		super();
	}
	
	public UserException(String message){
		super(message);
	}
	
	public UserException(String message, Throwable cause){
		super(message, cause);
	}
}
