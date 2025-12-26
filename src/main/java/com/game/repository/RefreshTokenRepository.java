package com.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

	Optional<RefreshToken> findByRefresToken(String refresToken);
	
}
