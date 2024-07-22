package com.global.users.enums;

import lombok.Getter;

@Getter
public enum ExceptionCodes {
	
	USER_NOT_FOUND(1, "Usuario no encontrado.", "Usuario no encontrado."),
	INVALID_TOKEN(2, "Token invalido.", "Token invalido"), 
	MISSING_TOKEN(3, "Request sin token", "Request sin token"),
	EMAIL_USER_ALREADY_EXIST(4, "El email ingresado ya existe para un usuario", "El email ingresado ya existe para un usuario"),
	EMAIL_FORMAT_EXCEPTION(5, "Formato del email invalido", "Formato del email invalido"),
	NUMBER_FORMAT_EXCEPTION(6,"El numero ingresado es incorrecto","El numero ingresado es incorrecto"),
	PASSWORD_FORMAT_EXCEPTION(7,"La Password ingresada no es valida","La Password ingresada no es valida"),
	DATA_INTEGRATION_VIOLATION_EXCEPTION(8,"Ocurrio un error con la BD contacte con el admnistrador", null),
	USER_ALREADY_EXIST(9, "El usuario ya existe", "Usuario Existente");

	private int code;
	private String userMessage;
	private String systemMessage;

	private ExceptionCodes(int code, String userMessage, String systemMessage) {
		this.code = code;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
	}

}
