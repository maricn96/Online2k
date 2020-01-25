package com.auth.authserver.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.authserver.dto.UserDTO;

public class MyUserDetails //implements UserDetails 
{
	
//	private String username;
//	private String password;
//	private Collection<? extends GrantedAuthority> authorities;
//	
//	public MyUserDetails(UserDTO user) {
//		this.username = user.getUsername();
//		this.password = user.getPassword();
//		
//		List<GrantedAuthority> auths = new ArrayList<>();
//		auths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//		this.authorities = auths;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.password;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
