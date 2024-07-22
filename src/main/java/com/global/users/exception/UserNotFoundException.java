package com.global.users.exception;

import com.global.users.enums.ExceptionCodes;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = -50510158400280774L;
	private ExceptionCodes exceptionCode;

	public UserNotFoundException() {
		super();
		exceptionCode = ExceptionCodes.USER_NOT_FOUND;
		
	}

}
