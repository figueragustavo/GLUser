package com.global.users.controller.handler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.global.users.DTO.ResponseError;
import com.global.users.enums.ExceptionCodes;
import com.global.users.exception.EmailExistsException;
import com.global.users.exception.PassworNotValidException;
import com.global.users.exception.UserNotFoundException;
import com.global.users.handler.ExceptionHandlerController;

@WebMvcTest(ExceptionHandlerController.class)
public class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ExceptionHandlerController exceptionHandlerController;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleError() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<ResponseError> response = exceptionHandlerController.handleError(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("500", response.getBody().getCode());
        assertEquals("Test Exception", response.getBody().getDescription());
    }

    @Test
    void testHandleEmailExistsException() {
        EmailExistsException exception = mock(EmailExistsException.class);
        when(exception.getExceptionCode()).thenReturn(ExceptionCodes.EMAIL_USER_ALREADY_EXIST);

        ResponseEntity<ResponseError> response = exceptionHandlerController.handlePEmailExistFound(exception);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals("4", response.getBody().getCode());
        assertEquals("El email ingresado ya existe para un usuario", response.getBody().getDescription());
    }

    @Test
    void testHandleUserNotFoundException() {
        UserNotFoundException exception = mock(UserNotFoundException.class);
        when(exception.getExceptionCode()).thenReturn(ExceptionCodes.USER_NOT_FOUND);

        ResponseEntity<ResponseError> response = exceptionHandlerController.handlePersonNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("1", response.getBody().getCode());
        assertEquals("Usuario no encontrado.", response.getBody().getDescription());
    }

    @Test
    void testEmailFormatException() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        ExceptionCodes exCode = ExceptionCodes.EMAIL_FORMAT_EXCEPTION;

        ResponseEntity<ResponseError> response = exceptionHandlerController.emailFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.valueOf(exCode.getCode()), response.getBody().getCode());
        assertEquals(exCode.getUserMessage(), response.getBody().getDescription());
    }

    @Test
    void testPassFormatException(){
        PassworNotValidException exception = mock(PassworNotValidException.class);
        when(exception.getExceptionCode()).thenReturn(ExceptionCodes.PASSWORD_FORMAT_EXCEPTION);

        ResponseEntity<ResponseError> response = exceptionHandlerController.passFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("7", response.getBody().getCode());
        assertEquals("La Password ingresada no es valida", response.getBody().getDescription());
    }
}
