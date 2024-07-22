package com.global.users.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserResponseDTO {
	
	private UUID id;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private boolean isActive;
	private String token;

}
