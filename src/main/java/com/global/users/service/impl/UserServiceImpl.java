package com.global.users.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.users.DTO.UserLoginResponseDTO;
import com.global.users.entity.User;
import com.global.users.exception.EmailExistsException;
import com.global.users.exception.UserNotFoundException;
import com.global.users.repository.UserRepository;
import com.global.users.security.JWTToken;
import com.global.users.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTToken jwtToken;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) throws Exception {
	
		this.existsEmail(user);
		user.setCreated(LocalDateTime.now());
		user.setActive(true);
		user.setLastLogin(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User newUser = this.userRepository.save(user);
		newUser.setToken(this.generateTokenById(newUser.getId()));

		return newUser;
	}

	@Override
	public UserLoginResponseDTO getUserById(UUID id) throws UserNotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		User user = null;
		this.isPresentUser(userOpt);
		user = userOpt.get();
		user.setLastLogin(LocalDateTime.now());
		
		return modelMapper.map(user, UserLoginResponseDTO.class);
	}
	
	public String generateTokenById(UUID id) {
		String token = jwtToken.getJWTToken(id);
		userRepository.updateTokenById(token, id);
		return token;
	}

	private void existsEmail(User user) throws EmailExistsException {
		if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
			throw new EmailExistsException();
		}
	}
	
	private void isPresentUser(Optional<User> user) throws UserNotFoundException {
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
	}
}
