package com.global.users.service;

import java.util.UUID;

import com.global.users.DTO.UserLoginResponseDTO;
import com.global.users.entity.User;
import com.global.users.exception.UserNotFoundException;

public interface UserService {
	
	public User save(User user) throws Exception;
	
	public UserLoginResponseDTO getUserById(UUID id) throws UserNotFoundException;

}
