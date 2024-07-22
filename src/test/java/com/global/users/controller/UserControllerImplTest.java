package com.global.users.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
//UserControllerTest.java
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.global.users.DTO.PhoneDTO;
import com.global.users.DTO.UserLoginResponseDTO;
import com.global.users.DTO.UserRequestDTO;
import com.global.users.DTO.UserResponseDTO;
import com.global.users.controller.impl.UserControllerImpl;
import com.global.users.entity.Phone;
import com.global.users.entity.User;
import com.global.users.exception.EmailExistsException;
import com.global.users.service.UserService;
import com.global.users.utils.TestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebMvcTest(UserControllerImpl.class)
public class UserControllerImplTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private ModelMapper modelMapper;

	@Test
	public void testSignUp() throws Exception {
		
		

		final List<PhoneDTO> phones = new ArrayList<>();
		PhoneDTO phone = new PhoneDTO();
		phone.setNumber("1234");
		phone.setCityCode("123");
		phone.setCountryCode("549");
		phones.add(phone);

		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setName("jose");
		userRequestDTO.setEmail("prueba@prueba.com");
		userRequestDTO.setPassword("P433word");
		userRequestDTO.setPhones(phones);
		User newUser = new User();
		newUser.setId(UUID.randomUUID());
		newUser.setName("jose");
		newUser.setEmail("prueba@prueba.com");
		newUser.setPassword("P433word");
		UserResponseDTO userResponseDTO = new UserResponseDTO();

		userResponseDTO.setId(newUser.getId());
		userResponseDTO.setActive(true);
		when(userService.save(any(User.class))).thenReturn(newUser);
		when(modelMapper.map(any(User.class), eq(UserResponseDTO.class))).thenReturn(userResponseDTO);

		mockMvc.perform(post("/api/v1/user/sign-up").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"jose\",\"email\":\"prueba@prueba.com\",\"password\":\"P433word\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testLogin() throws Exception {
	
		UUID userId = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");
		final List<PhoneDTO> phones = new ArrayList<>();
		PhoneDTO phone = new PhoneDTO();
		phone.setNumber("1234");
		phone.setCityCode("123");
		phone.setCountryCode("549");
		phones.add(phone);

		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setName("jose");
		userRequestDTO.setEmail("prueba@prueba.com");
		userRequestDTO.setPassword("P433word");
		userRequestDTO.setPhones(phones);
		User newUser = new User();
		newUser.setId(UUID.randomUUID());
		newUser.setName("jose");
		newUser.setEmail("prueba@prueba.com");
		newUser.setPassword("P433word");
		
		UserLoginResponseDTO userLoginDTO = new UserLoginResponseDTO();
		userLoginDTO.setName("jose");
		userLoginDTO.setEmail("prueba@prueba.com");
		userLoginDTO.setPassword("Pa33word");

		Mockito.when(userService.save(any(User.class))).thenReturn(newUser);
		 Mockito.when(userService.getUserById(userId))
         .thenReturn(userLoginDTO);


		mockMvc.perform(get("/api/v1/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer "+newUser.getId()))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void UserExist() throws Exception {

		Mockito.when(userService.save(any(User.class)))
        .thenThrow(EmailExistsException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/sign-up").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}
}
