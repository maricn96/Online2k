package com.auth.authserver.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.auth.authserver.dto.UserToLoginDTO;
import com.auth.authserver.jwt.JwtTokenUtils;
import com.auth.authserver.model.User;
import com.auth.authserver.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl {
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtils jwtTokenProvider;
	 
	public LoginServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenProvider) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/*
	 * Logovanje korisnika
	 */
	public String login(UserToLoginDTO userDto) {

		User user = userRepository.findByEmail(userDto.getEmail());
		
		if(user == null) {
			return "NOT_FOUND_ERR";
		}else if(!user.getActive()) {
			return "NOT_ACTIVE_ERR";
		}
		
		try {
			String jwt = loginHelper(userDto.getEmail(), userDto.getPassword());
			ObjectMapper mapper = new ObjectMapper();
		
			return mapper.writeValueAsString(jwt);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return "UNKNOWN_ERR";
		}
	}
	
	private String loginHelper(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		return jwtTokenProvider.createToken(email);
	}
	

}
