package com.zestindia.productapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zestindia.productapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}