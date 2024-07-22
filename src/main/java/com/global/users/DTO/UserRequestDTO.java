package com.global.users.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
	
	private String name;

	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	private List<PhoneDTO> phones;

}
