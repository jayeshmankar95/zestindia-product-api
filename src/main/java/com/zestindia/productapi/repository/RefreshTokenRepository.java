package com.zestindia.productapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zestindia.productapi.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
}