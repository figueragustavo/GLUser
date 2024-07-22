package com.global.users.exception;

import com.global.users.enums.ExceptionCodes;

import lombok.Getter;

@Getter
public class PassworNotValidException extends Exception {

	private static final long serialVersionUID = 2636506319135616515L;
	
	private ExceptionCodes exceptionCode;

	public PassworNotValidException() {
		super();
		exceptionCode = ExceptionCodes.PASSWORD_FORMAT_EXCEPTION;
	}

}
