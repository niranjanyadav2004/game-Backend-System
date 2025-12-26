package com.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.Game;
import com.game.entity.Player;
import com.game.entity.PlayerProgression;



public interface PlayerProgressionRepository extends JpaRepository<PlayerProgression, Long> {

	 Optional<PlayerProgression> findByPlayerAndGame(Player player, Game game);
	 Optional<PlayerProgression> findTopByPlayerIdOrderByUpdatedAtDesc(Long playerId);
	
}
