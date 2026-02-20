package com.zestindia.productapi.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access.expiration}")
	private long accessExpiration;

	@Value("${jwt.refresh.expiration}")
	private long refreshExpiration;

	private Key getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateAccessToken(String username, String role) {

		return Jwts.builder().subject(username).claim("role", role).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + accessExpiration)).signWith(getKey()).compact();
	}

	public String extractRole(String token) {

		return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload().get("role",
				String.class);
	}

	public String generateRefreshToken(String username) {

		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + refreshExpiration)).signWith(getKey()).compact();
	}

	public String extractUsername(String token) {

		return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
