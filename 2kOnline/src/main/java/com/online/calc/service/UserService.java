package com.online.calc.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.online.calc.dto.UserDTO;
import com.online.calc.model.User;

public interface UserService {

	User getOne(ObjectId objectId);
	
	User getOneByUsername(String username);

	List<User> getAll();

	User create(UserDTO userDto);

	User update(ObjectId id, UserDTO userDto);

	void delete(ObjectId id);

	

}
