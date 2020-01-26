package com.auth.authserver.jwt;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth.authserver.config.UserDetails;
import com.auth.authserver.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:86400000}") //1 dan
	private long validityInMilliseconds = 86400000;
	
	
	@Autowired
	private UserDetails myUserDetails;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	
	public String createToken(String email) {
		List<SimpleGrantedAuthority> userTypes = new ArrayList<SimpleGrantedAuthority>();
		userTypes.add(new SimpleGrantedAuthority("KORISNIK")); //user-i su svi koji koriste program, KORISNIK je obican korisnik, ADMIN je admin (logicno)
		userTypes.add(new SimpleGrantedAuthority("ADMIN"));
		
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("auth", userTypes);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		return Jwts.builder().setClaims(claims)
					.setIssuedAt(now)
					.setExpiration(validity)
					.signWith(SignatureAlgorithm.HS256, secretKey)
					.compact();
	}
	
	
	public Authentication getAuthentication(String token) {
		org.springframework.security.core.userdetails.UserDetails userDetails = myUserDetails.loadUserByUsername(getEmail(token)); //nesto kao principal (tj podaci o korisniku)
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
	}
	
	//reversed proces koji izvlaci email iz tokena (vrv poredjenjem jer je hashovano)
	public String getEmail(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7, bearerToken.length());
		
		return null;
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or invalid web token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
