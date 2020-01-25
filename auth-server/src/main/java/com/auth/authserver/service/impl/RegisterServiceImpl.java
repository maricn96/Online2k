package com.auth.authserver.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authserver.dto.UserDTO;
import com.auth.authserver.dto.UserToRegDTO;
import com.auth.authserver.model.User;
import com.auth.authserver.repository.UserRepository;
import com.auth.authserver.service.UserService;

@Service
public class RegisterServiceImpl {
	
	private final UserRepository userRepository;
	
	private final UserService userService;
	
	public RegisterServiceImpl(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	public String register(UserToRegDTO userDto) {
		
		if(userRepository.existsByEmail(userDto.getEmail())) {
			return "EXISTS_ERR";
		}
		else if(!userDto.getPassword().equals(userDto.getPasswordRepeated())) {
			return "PASS_ERR";
		}
		else {
			UserDTO user = new UserDTO(userDto.getEmail(), this.encode().encode(userDto.getPassword()), "USER", false);
			userRepository.save(user.asBean());
			
			return "REGISTERED";
		}
		
	}
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
}
