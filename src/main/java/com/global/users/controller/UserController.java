package com.global.users.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.global.users.DTO.UserRequestDTO;
import com.global.users.exception.EmailExistsException;

public interface UserController {

	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws EmailExistsException, Exception;
	
	public ResponseEntity<?> getUserByToken(@RequestAttribute("id") UUID id) throws Exception;
}
