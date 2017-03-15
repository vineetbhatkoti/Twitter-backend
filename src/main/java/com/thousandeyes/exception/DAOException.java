package com.thousandeyes.exception;


/*
 * @desc: This is a custom class for handling all the DAO Exceptions
 * @author: Vineet Bhatkoti
 */

public class DAOException  extends Exception {

	private static final long serialVersionUID = 2164486112280869357L;

	public DAOException(){
		super();
	}
	
	public DAOException(String message){
		super(message);
	}
}
