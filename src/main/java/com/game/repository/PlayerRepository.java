package com.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByDeviceId(String deviceId);
	Optional<Player> findByUsername(String username);
	
}
