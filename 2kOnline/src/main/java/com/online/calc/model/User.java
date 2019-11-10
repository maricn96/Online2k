package com.online.calc.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.online.calc.dto.MealDTO;
import com.online.calc.dto.UserDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Document
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class User {
	
	@Id
	private ObjectId id;
	private String username;
	private String password;
	private String email;
	private String role;

	public User(String username, String password, String email, String role) {
		Assert.notNull(username, "Date of meal must not be empty!");
		Assert.notNull(password, "User must not be empty!");
		Assert.notNull(email, "Amount must not be empty!");
		Assert.notNull(role, "Role must not be empty");
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public User(ObjectId id, String username, String password, String email, String role) {
		this(username, password, email, role);
		Assert.notNull(id, "Id must not be empty!");
		this.id = id;
	}
	
	public User(UserDTO dto) {
		if(dto.getId() != null) 
			this.id = new ObjectId(dto.getId());
		
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.email = dto.getEmail();
		this.role = dto.getRole();
	}
	
	public UserDTO asDto() {
		return new UserDTO(this);
	}
	
	
	
}
