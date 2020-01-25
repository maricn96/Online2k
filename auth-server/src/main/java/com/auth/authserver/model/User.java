package com.auth.authserver.model;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.auth.authserver.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String role;
	
	@NotNull
	private Boolean active;
	
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String email, String password, String role, Boolean active) {
		this(email, password);
		this.role = role;
		this.active = active;
	}

	public UserDTO asDTO() {
		return new UserDTO(id, email, password, role, active);
	}

}
