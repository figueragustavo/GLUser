package com.global.users.exception;

import com.global.users.enums.ExceptionCodes;

import lombok.Getter;

@Getter
public class EmailExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	private final ExceptionCodes exceptionCode;

	public EmailExistsException() {
		super();
		exceptionCode = ExceptionCodes.EMAIL_USER_ALREADY_EXIST;
	}
}
