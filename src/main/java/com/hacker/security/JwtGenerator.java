package com.hacker.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.hacker.model.UserPrinciple;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	//private static Logger logger  = LoggerFactory.getLogger(JwtGenerator.class);
	@Value("${practice.key}")
	private String secretKey;
	@Value("${practice.expirationTime}")
	private long expirationTime;
	
	public String generateToken(Authentication authentication){
		
		System.out.println("In generate");
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		System.out.println("-------->"+userPrinciple);

		String token = Jwts.builder()
				.setSubject(userPrinciple.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+expirationTime))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		System.out.println("from generator");
		return token;
	}
	public boolean validateToken(String token){
		try{
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}
		catch(Exception ex){
			//logger.error("JwtToken validation error->{}", ex.getMessage());
			System.out.println(ex.getMessage());
		}
		return false;
	}
	public String getUsernameFromToken(String token){
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
}
