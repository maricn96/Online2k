package com.auth.authserver.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.auth.authserver.service.impl.MyUserDetailsServiceImpl;

@Configuration
//@EnableAuthorizationServer 
public class AuthorizationServerConfig //extends AuthorizationServerConfigurerAdapter
{

//	@Value("${user.oauth.clientid}")
//	private String clientID;
//	
//	@Value("${user.oauth.clientSecret}")
//	private String clientSecret;
//	
//	@Value("${user.oauth.redirectUris}")
//	private String redirectURLs;
//	
//	private final PasswordEncoder passwordEncoder;
//	
//	public AuthorizationServerConfig(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder;
//	}
//	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		oauthServer.tokenKeyAccess("permitAll()")
//				.checkTokenAccess("isAuthenticated()");
//	}
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer client) throws Exception {
//		client.inMemory()
//			.withClient(clientID)
//			.secret(passwordEncoder.encode(clientSecret))
//			.authorizedGrantTypes("authorization_code")
//			.scopes("user_info")
//			.autoApprove(true)
//			.redirectUris(redirectURLs);
//	}
	
	
}
