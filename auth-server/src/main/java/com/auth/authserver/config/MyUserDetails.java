package com.auth.authserver.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.authserver.model.User;
import com.auth.authserver.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("User with email " + email + " not found!");
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority("KORISNIK")); //posle dodati admina (valjda)
		grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true, true, grantedAuthorities);
	}

	
	
}
