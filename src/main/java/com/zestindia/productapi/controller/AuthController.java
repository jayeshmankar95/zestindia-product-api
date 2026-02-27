package com.zestindia.productapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zestindia.productapi.dto.*;
import com.zestindia.productapi.entity.User;
import com.zestindia.productapi.repository.UserRepository;
import com.zestindia.productapi.security.JwtUtil;
import com.zestindia.productapi.service.RefreshTokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final RefreshTokenService refreshTokenService;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
			RefreshTokenService refreshTokenService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.refreshTokenService = refreshTokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		String access = jwtUtil.generateAccessToken(user.getUsername(), user.getRole());

		String refresh = jwtUtil.generateRefreshToken(user.getUsername());

		refreshTokenService.save(refresh, user.getUsername());

		return ResponseEntity.ok(new AuthResponse(access, refresh));
	}
}