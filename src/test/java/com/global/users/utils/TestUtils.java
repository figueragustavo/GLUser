package com.global.users.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.global.users.DTO.PhoneDTO;
import com.global.users.DTO.UserRequestDTO;
import com.global.users.entity.User;

public class TestUtils {
	
	public static UserRequestDTO GET_REQUEST() {

		final List<PhoneDTO> phones = new ArrayList<>();
		PhoneDTO phone = new PhoneDTO();
		phone.setNumber("12345678");
		phone.setCityCode("11");
		phone.setCountryCode("549");
		phones.add(phone);

		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setName("jose");
		userRequestDTO.setEmail("prueba@prueba.com");
		userRequestDTO.setPassword("P433wordd");
		userRequestDTO.setPhones(phones);
		
		return userRequestDTO;
	}
	
	public static User GET_USER(UserRequestDTO userRequest) {
		UUID userId = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");
		User newUser = new User();
		newUser.setId(userId);
		newUser.setName(userRequest.getName());
		newUser.setEmail(userRequest.getEmail());
//		newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		newUser.setPassword("$2a$10$9vrYvI3.PVXQm6Dz0TMWjeKjgUGAJaCex92sapBecu.pLSMpcHABO");
		newUser.setCreated(LocalDateTime.now());
		newUser.setLastLogin(LocalDateTime.now());
		return newUser;
	}
}
