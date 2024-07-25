package com.global.users.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.global.users.exception.UserNotFoundException;
import com.global.users.security.JWTToken;
import com.global.users.service.UserService;
import com.global.users.service.impl.UserServiceImpl;
import com.global.users.util.PasswordValidator;
import com.global.users.utils.TestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(UserControllerImpl.class)
public class UserControllerImplTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserControllerImpl userController;

	@MockBean
	private UserServiceImpl userService;

	@MockBean
	private ModelMapper modelMapper;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private PasswordValidator passwordValidator;

	@Mock
	private JWTToken jwtToken;

	@Test
	public void testSignUp() throws Exception {

		UserRequestDTO userRequest = TestUtils.GET_REQUEST();
		User newUser = TestUtils.GET_USER(userRequest);

		UserResponseDTO userResponseDTO = new UserResponseDTO();

		userResponseDTO.setId(newUser.getId());
		userResponseDTO.setActive(newUser.isActive());
		when(userService.save(newUser)).thenReturn(newUser);
		when(jwtToken.getJWTToken(newUser.getId())).thenReturn("token");
		when(modelMapper.map(any(User.class), eq(UserResponseDTO.class))).thenReturn(userResponseDTO);

		mockMvc.perform(post("/api/v1/user/sign-up").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"jose\",\"email\":\"prueba@prueba.com\",\"password\":\"P433word\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testLogin() throws Exception {

		UUID userId = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");
		UserRequestDTO userRequest = TestUtils.GET_REQUEST();
		User newUser = TestUtils.GET_USER(userRequest);

		UserLoginResponseDTO userLoginDTO = new UserLoginResponseDTO();
		userLoginDTO.setName(newUser.getName());
		userLoginDTO.setEmail(newUser.getEmail());
		userLoginDTO.setActive(newUser.isActive());
		userLoginDTO.setPassword(newUser.getPassword());
		userLoginDTO.setCreated(newUser.getCreated());
		userLoginDTO.setId(newUser.getId());
		userLoginDTO.setLastLogin(newUser.getLastLogin());
		userLoginDTO.setToken(newUser.getToken());
//		userLoginDTO.setPhones(newUser.getPhones());
		

		when(userService.save(any(User.class))).thenReturn(newUser);
		when(jwtToken.getJWTToken(newUser.getId())).thenReturn("token");
		doNothing().when(userService).existsEmail(any(User.class));
		when(modelMapper.map(any(User.class), eq(UserLoginResponseDTO.class))).thenReturn(userLoginDTO);
		when(userService.getUserById(userId)).thenReturn(userLoginDTO);

		mockMvc.perform(get("/api/v1/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization","Bearer " + newUser.getToken()))
		.andExpect(status().is4xxClientError());
		
		
	}

}
