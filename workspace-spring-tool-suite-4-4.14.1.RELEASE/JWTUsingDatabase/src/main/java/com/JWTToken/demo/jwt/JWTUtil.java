package com.JWTToken.demo.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	public static final long  TOKEN_VALIDITY= 600000;
	public String SECRET_KEY ="shubhamIse";
	
	private String createToken(Map<String,Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, userDetails.getUsername());
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		final Claims claim=extractAllClaims(token);
		return claimResolver.apply(claim);
	}
	
	public String extractUsernameFromToken(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails details) {
		String username= extractUsernameFromToken(token);
		return (username.equals(details.getUsername()) && !isTokenExpired(token));
	}
}

