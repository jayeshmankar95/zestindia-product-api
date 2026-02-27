package com.zestindia.productapi.service;

import org.springframework.stereotype.Service;
import com.zestindia.productapi.repository.RefreshTokenRepository;
import com.zestindia.productapi.entity.RefreshToken;

import java.time.Instant;

@Service
public class RefreshTokenService {

	private final RefreshTokenRepository repository;

	public RefreshTokenService(RefreshTokenRepository repository) {
		this.repository = repository;
	}

	public void save(String token, String username) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(token);
		refreshToken.setUsername(username);
		refreshToken.setExpiryDate(Instant.now().plusSeconds(86400));
		repository.save(refreshToken);
	}

	public void validate(String token) {
		repository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid refresh token"));
	}
}