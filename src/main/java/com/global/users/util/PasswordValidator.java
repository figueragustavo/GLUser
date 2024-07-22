package com.global.users.util;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.global.users.exception.PassworNotValidException;

//@Component
public class PasswordValidator {
	
//	@Value("${user.password.regex}")
//    private String passwordRegex;

    public void validatePassword(String password) throws PassworNotValidException {
        if (password == null || !password.matches("^(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]).{8,12}$")) {
        	throw new PassworNotValidException();
        }
    }

}
