package com.jss.springbootdemo.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 2504358420363254503L;

	public UserServiceException(String message) {
		super(message);
	}
}
