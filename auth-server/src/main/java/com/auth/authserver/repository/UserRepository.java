package com.auth.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.authserver.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);

	
	
}
