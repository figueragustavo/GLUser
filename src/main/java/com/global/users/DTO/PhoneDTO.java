package com.global.users.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO {
	
	private String number;
	private String cityCode;
	private String countryCode;

}
