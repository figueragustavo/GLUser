package com.global.users.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonRootName("Error")
public class ResponseError {
	
	private String code;
	private String description;
	private LocalDateTime timestamp;

}
