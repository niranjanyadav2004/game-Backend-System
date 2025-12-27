package com.game.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.game.DTO.ProgressionRequest;
import com.game.entity.Game;
import com.game.entity.Player;
import com.game.entity.PlayerProgression;
import com.game.repository.GameRepository;
import com.game.repository.PlayerProgressionRepository;
import com.game.repository.PlayerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerProgressionService {

	private final PlayerRepository playerRepository;
	private final GameRepository gameRepository;
	private final PlayerProgressionRepository playerProgressionRepository;
	
	public PlayerProgression saveProgress(ProgressionRequest request) {
		Player player = playerRepository.findById(request.getPlayerId()).orElseThrow(()->new EntityNotFoundException("Player not found"));
		Game game = gameRepository.findById(request.getGameId()).orElseThrow(()->new EntityNotFoundException("Game not found"));
		
		PlayerProgression progression = playerProgressionRepository.findByPlayerAndGame(player, game).orElse(new PlayerProgression());
		
		progression.setPlayer(player);
        progression.setGame(game);
        progression.setLevel(request.getLevel());
        progression.setRank(request.getRank());
        progression.setGold(request.getGold());
        progression.setCash(request.getCash());
        progression.setGem(request.getGem());
        progression.setRewards(request.getRewards());
        progression.setLastActiveAt(LocalDateTime.now());
        progression.setUpdatedAt(LocalDateTime.now());
		
		return playerProgressionRepository.save(progression);
	}
	
	public PlayerProgression getPlayerProgress(Long playerId) {
		System.out.println("Progression from db");
		Player player = playerRepository.findById(playerId).orElseThrow(()->new EntityNotFoundException("Player Not found"));
		
		return playerProgressionRepository
	            .findTopByPlayerIdOrderByUpdatedAtDesc(player.getId())
	            .orElseThrow(()->new EntityNotFoundException("Progression not found for player"));
	}
	
}
