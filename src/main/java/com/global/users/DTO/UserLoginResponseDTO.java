package com.global.users.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginResponseDTO {

	private UUID id;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private String token;
	private boolean isActive;
	@JsonInclude(Include.NON_NULL)
	private String name;
	@Email
	private String email;
	private String password;
	@JsonInclude(Include.NON_NULL)
	private List<PhoneDTO> phones;
	
}
