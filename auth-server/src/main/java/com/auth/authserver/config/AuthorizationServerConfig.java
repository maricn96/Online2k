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
@EnableAuthorizationServer //enejbluje autorizaciju
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	/*
	 * Radjeno prema: https://developer.okta.com/blog/2019/03/12/oauth2-spring-security-guide
	 * Vraca JSON web token ako se klijent ulogovao uspesno
	 * Koristi samo ovu klasu i securityConfig, kao i UserController, ostalo je nebitno..
	 */
	
	@Value("${user.oauth.clientid}")
	private String clientID;
	
	@Value("${user.oauth.clientSecret}")
	private String clientSecret;
	
	@Value("${user.oauth.redirectUris}")
	private String redirectURLs;
	
	private final PasswordEncoder passwordEncoder;
	
	public AuthorizationServerConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception {
		client.inMemory()
			.withClient(clientID)
			.secret(passwordEncoder.encode(clientSecret))
			.authorizedGrantTypes("authorization_code")
			.scopes("user_info")
			.autoApprove(true)
			.redirectUris(redirectURLs);
	}
	
	
	
	
	
	
	
	
	/*
	 * Ovo ispod je sa nekog drugog sajta
	 */
	
//	//generise tokene specificno za klijenta
//
//	static final String CLIENT_ID = "devglan-client";
//	//static final String CLIENT_SECRET = "devglan-secret";
//	static final String CLIENT_SECRET = "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG";
//	static final String GRANT_TYPE_PASSWORD = "password";
//	static final String AUTHORIZATION_CODE = "authorization_code";
//	static final String REFRESH_TOKEN = "refresh_token";
//	static final String IMPLICIT = "implicit";
//	static final String SCOPE_READ = "read";
//	static final String SCOPE_WRITE = "write";
//	static final String TRUST = "trust";
//	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
//	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
//	
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private MyUserDetailsServiceImpl myUserDetailsService;
//	
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey("as466gf");
//		return converter;
//	}
//	
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//	}
//	
//	 @Override
//	    public void configure(
//	            AuthorizationServerSecurityConfigurer oauthServer)
//	            throws Exception {
//	        oauthServer
//	                .tokenKeyAccess("permitAll()")
//	                .checkTokenAccess("isAuthenticated()");
//	    }
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception { 
//		configurer.inMemory()
//					.withClient(CLIENT_ID)
//					.secret(CLIENT_SECRET).authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
//					.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
//					.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
//	}
//	
//	@Override
//    public void configure(
//            AuthorizationServerEndpointsConfigurer endpoints)
//            throws Exception {
//
//        endpoints.tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter())
//                .authenticationManager(authenticationManager)
//                .userDetailsService(myUserDetailsService);
//    }
//	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://db_calcauth:3306/auth");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//		return dataSource;
//	}
//	
//	
	
}
