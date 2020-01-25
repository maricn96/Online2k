package com.auth.authserver.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.auth.authserver.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

	private Long id;
	private String password;
	private String passwordRepeated;
	private String email;
	private String role;
	private boolean active;
	
	
	public UserDTO(String email, String password, String role, boolean active) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = active;
	}
	
	public UserDTO(Long id, String email, String password, String role, boolean active) {
		this(email, password, role, active);
		this.id = id;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.active = user.getActive();
	}

	public User asBean() {
		return new User(id, email, password, role, active);
	}


	
}
