package com.auth.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.authserver.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public boolean existsByEmail(String email);

	public User findByEmail(String email);

	
	
}
