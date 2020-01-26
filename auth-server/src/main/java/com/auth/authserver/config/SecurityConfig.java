package com.auth.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.authserver.jwt.JwtTokenFilterConfigurer;
import com.auth.authserver.jwt.JwtTokenUtils;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig// extends WebSecurityConfigurerAdapter
{
	

//	@Autowired
//	private JwtTokenUtils jwtTokenProvider;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//
//	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().disable();
////		http.authorizeRequests()
////		.antMatchers("/**")
////		.permitAll()
////		.anyRequest()
////		.anonymous()
////		.and()
////		.csrf()
////		.disable();
//		
////		http.csrf().disable();
////		http.cors().disable();
//		
////		//nece kreirati nikakvu sesiju 
////		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////		
////		//ono cemu neregistrovani korisnik sme da pristupi
////		http.authorizeRequests().antMatchers("/*", "/login", "/register", "/user").permitAll();//.anyRequest().authenticated();
////		
////		http.exceptionHandling().accessDeniedPage("/adminpanel"); //npr da panel admina trci na ovom linku zabranicemo ako nema dovoljno ovlascenja
////		
////		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
//		
//	}
//	
////	@Override
////	public void configure(WebSecurity web) {
////		//sve sto ce se ignorisati za web
////		web.ignoring().antMatchers("/webjars/**")//
////				.antMatchers("/public")//
////				.antMatchers("/main**")//
////				.antMatchers("/inline**")//
////				.antMatchers("/polyfills**")//
////				.antMatchers("/styles**")//
////				.antMatchers("/favicon.ico")//
////				.antMatchers("/scripts**")//
////				.antMatchers("/glyphicons**")//
////				.antMatchers("/fontawesome**")//
////				.antMatchers("/vendor**")//
////				.antMatchers("/assets/**")//
////				.antMatchers("/Poppins**")//
////				.antMatchers("/h2-console");
////	}
//	
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	
}
