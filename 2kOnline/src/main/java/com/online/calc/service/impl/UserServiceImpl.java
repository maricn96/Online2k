package com.online.calc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.online.calc.dto.UserDTO;
import com.online.calc.dto.UserDTO;
import com.online.calc.model.User;
import com.online.calc.model.User;
import com.online.calc.repository.FoodRepository;
import com.online.calc.repository.UserRepository;
import com.online.calc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

private static final Logger log = LogManager.getLogger(FoodServiceImpl.class);
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public User getOne(ObjectId id) {
		log.info("UserService GET ONE by id %s", id);
		return userRepository.findById(id).get();
	}
	
	@Override
	public User getOneByUsername(String username) {
		log.info("UserService GET ONE BY USERNAME %s", username);
		return userRepository.findByUsername(username);
	}
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User create(UserDTO userDto) {
		log.info("UserService CREATE %s", userDto);
		return userRepository.save(new User(userDto));
	}


	@Override
	public User update(ObjectId id, UserDTO userDto) {
		log.info("UserService UPDATE with id %s", id);
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())		{
			//baci neki eksepsn
		}
		return userRepository.save(userDto.asBean());
	}


	@Override
	public void delete(ObjectId id) {
		log.info("UserService DELETE with id %s", id);
		userRepository.deleteById(id);
	}




}
