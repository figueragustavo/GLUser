package com.global.users.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.global.users.DTO.ResponseError;
import com.global.users.enums.ExceptionCodes;
import com.global.users.exception.EmailExistsException;
import com.global.users.exception.PassworNotValidException;
import com.global.users.exception.UserNotFoundException;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
	
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ResponseError> handleError(Exception ex) {
		 ex.printStackTrace();
		 log.error(ex.getMessage());
		 return new ResponseEntity<>(ResponseError.builder().code("500").description(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ExceptionHandler(EmailExistsException.class)
	  public ResponseEntity<ResponseError> handlePEmailExistFound(EmailExistsException ex) {
		 log.error(ex.getExceptionCode().getCode()+"--"+ex.getExceptionCode().getUserMessage());
		 return new ResponseEntity<>(ResponseError.builder()
				 .code(String.valueOf(ex.getExceptionCode().getCode()))
				 .description(ex.getExceptionCode().getUserMessage())
				 .build(), HttpStatus.FOUND);
	  }
	 @ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<ResponseError> handlePersonNotFound(UserNotFoundException ex) {
		 log.error(ex.getExceptionCode().getCode()+"--"+ex.getExceptionCode().getUserMessage());
		 return new ResponseEntity<>(ResponseError.builder()
				 .code(String.valueOf(ex.getExceptionCode().getCode()))
				 .description(ex.getExceptionCode().getUserMessage())
				 .build(), HttpStatus.NOT_FOUND);
	 }
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ResponseError> emailFormatException(MethodArgumentNotValidException e) {
		 ExceptionCodes ex = ExceptionCodes.EMAIL_FORMAT_EXCEPTION;
		 log.error(ex.getCode()+"--"+ex.getUserMessage());
		 return new ResponseEntity<>(ResponseError.builder()
				 .code(String.valueOf(ex.getCode()))
				 .description(ex.getUserMessage())
				 .build(), HttpStatus.BAD_REQUEST);
	 }
	 @ExceptionHandler(PassworNotValidException.class)
	  public ResponseEntity<ResponseError> passFormatException(PassworNotValidException ex) {
		 log.error(ex.getExceptionCode().getCode()+"--"+ex.getExceptionCode().getUserMessage());
		 return new ResponseEntity<>(ResponseError.builder()
				 .code(String.valueOf(ex.getExceptionCode().getCode()))
				 .description(ex.getExceptionCode().getUserMessage())
				 .build(), HttpStatus.FOUND);
	  }
//	 @ExceptionHandler(SignatureException.class)
//	 public ResponseEntity<ResponseError> handlePersonNotFound(SignatureException e) {
//		 ExceptionCodes ex = ExceptionCodes.INVALID_TOKEN;
//		 log.error(ex.getCode()+"--"+ex.getUserMessage());
//		 return new ResponseEntity<>(ResponseError.builder()
//				 .code(String.valueOf(ex.getCode()))
//				 .description(ex.getUserMessage())
//				 .build(), HttpStatus.UNAUTHORIZED);
//	 }

}
