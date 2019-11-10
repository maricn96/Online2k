package com.online.calc.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;

import com.online.calc.model.Meal;
import com.online.calc.model.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@ApiModel(description = "DTO object that represents user bean.")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	@ApiModelProperty(notes = "Generated id.")
	private String id;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String role;
	

	public UserDTO(ObjectId id, String username, String password, String email, String role) {
		init(username, password, email, role);
		this.id = id.toString();
	}
	
	public UserDTO(User user) {
		this.id = user.getId().toString();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.role = user.getRole();
	}
	
	public void init(String username, String password, String email, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public User asBean() {
		return new User(this);
	}

}
