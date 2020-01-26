package com.auth.authserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth.authserver.dto.UserDTO;
import com.auth.authserver.model.User;
import com.auth.authserver.repository.UserRepository;
import com.auth.authserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> getAllUsers() {
		System.out.println("getAllUsers()");
		List<UserDTO> users = new ArrayList<>();
		
		for(User user : userRepository.findAll()) {
			users.add(user.asDTO());
		}
		
		return users;
	}

}
