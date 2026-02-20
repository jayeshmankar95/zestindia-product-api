package com.zestindia.productapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zestindia.productapi.dto.AuthResponse;
import com.zestindia.productapi.dto.LoginRequest;
import com.zestindia.productapi.dto.RefreshRequest;
import com.zestindia.productapi.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

		if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {

			String role = request.getRole(); // ADMIN or USER

			String access = jwtUtil.generateAccessToken(request.getUsername(), role);
			String refresh = jwtUtil.generateRefreshToken(request.getUsername());

			return ResponseEntity.ok(new AuthResponse(access, refresh));
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
