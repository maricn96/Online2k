package com.auth.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

	private String id;
	private String username;
	private String password;
	private String email;
	private String role;
}
