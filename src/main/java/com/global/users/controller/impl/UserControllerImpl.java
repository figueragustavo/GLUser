package com.global.users.controller.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.global.users.DTO.UserLoginResponseDTO;
import com.global.users.DTO.UserRequestDTO;
import com.global.users.DTO.UserResponseDTO;
import com.global.users.controller.UserController;
import com.global.users.entity.User;
import com.global.users.exception.UserNotFoundException;
import com.global.users.service.UserService;
import com.global.users.util.PasswordValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserControllerImpl implements UserController{

	@Autowired
	private UserService service;
	
	@Autowired
    private PasswordValidator passwordValidator;
	
	private ModelMapper modelMapper;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
		passwordValidator.validatePassword(userRequestDTO.getPassword());
		return new ResponseEntity<UserResponseDTO>(this.createUser(userRequestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUserByToken(@RequestAttribute("id") UUID id) throws Exception {
		return new ResponseEntity<UserLoginResponseDTO>(this.getUserById(id), HttpStatus.OK);
	}
	
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws Exception {
		User newUser = this.service.save(this.modelMapper.map(userRequestDTO, User.class));
		return this.modelMapper.map(newUser, UserResponseDTO.class);
	}
	
	private UserLoginResponseDTO getUserById(UUID id) throws UserNotFoundException {
		return this.service.getUserById(id);
	}
	
}
