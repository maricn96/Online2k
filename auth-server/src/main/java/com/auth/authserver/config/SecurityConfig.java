package com.auth.authserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * Ista prica, ovo je sa okta core sajta, ono ispod nesto od ranije
	 * Dakle ova klasa autentifikuje zahteve ka serveru. 
	 */
	
	@Value("${user.oauth.user.email}")
	private String email;
	
	@Value("${user.oauth.user.password}")
	private String password;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//dozvoljeno bilo sta, bez sekjuritija
		http.authorizeRequests()
			.antMatchers("/**", "/login", "/register", "/user")
			.permitAll()
			.anyRequest()
			.anonymous()
			.and()
			.csrf()
			.disable();
//		http.requestMatchers()
//			.antMatchers("/login", "/register", "/user", "/oauth/authorize")
//			.and()
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//			.permitAll();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(email).password("{noop}" + password).roles("USER"); //ovo noop resava problem za sifrovanje id-ja
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
	
	/*
	 * Nesto od ranije, nebitno ako ovo iznad radi..
	 */
	

//	private UserDetailsService myUserDetailsService;
//
//	public SecurityConfig(UserDetailsService myUserDetailsService) {
//		this.myUserDetailsService = myUserDetailsService;
//	}
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.myUserDetailsService).passwordEncoder(encoder());
//	}
//
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf()
//			.disable()
//			.anonymous()
//			.disable()
//			.authorizeRequests()
//			.antMatchers("/login")
//			.permitAll()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//			.loginPage("/login");
//	}
//
//	@Bean
//	public BCryptPasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}

}
