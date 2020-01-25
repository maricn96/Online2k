package com.auth.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserToRegDTO {

	private String password;
	private String passwordRepeated;
	private String email;
}
