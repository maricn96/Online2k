package com.auth.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auth.authserver.dto.UserDTO;

@Service
public class MyUserDetailsServiceImpl //implements UserDetailsService 
{

//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		//vidi samo ako ne namapira role onda treba novi dto bez rola
//		UserDTO user = restTemplate.getForObject("/user/getbyusername/" + username, UserDTO.class);
//		if(user == null) 
//			throw new UsernameNotFoundException("User with username " + username + " not found.");
//		
//		return new MyUserDetails(user);
//	}
}
