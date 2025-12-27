package com.game.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.game.DTO.ScoreRequest;
import com.game.entity.Game;
import com.game.entity.LeaderboardScore;
import com.game.entity.Player;
import com.game.repository.GameRepository;
import com.game.repository.PlayerRepository;
import com.game.repository.ScoreRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {

	private final PlayerRepository playerRepository;
	private final GameRepository gameRepository;
	private final ScoreRepository scoreRepository;
	
	
	@CacheEvict(value = {"globalLeaderboard","countryLeaderboard"}, allEntries = true)
	public LeaderboardScore submit(ScoreRequest request) {
		Player player = playerRepository.findById(request.getPlayerId())
				                        .orElseThrow(()->new EntityNotFoundException("Player not exist"));
		
		Game game = gameRepository.findById(request.getGameId())
		              .orElseThrow(()-> new EntityNotFoundException("Game not exist"));
		
		
		if(gameRepository.findById(request.getGameId()).isEmpty())
			    throw new EntityNotFoundException("Game not exist");
		
		LeaderboardScore scores = new LeaderboardScore();
		scores.setPlayer(player);
		scores.setGame(game);
		scores.setScore(request.getScore());
		scores.setCountry(player.getCountry());
		scores.setPlayeAt(LocalDateTime.now());
		
		return scoreRepository.save(scores);
	}
	
	
	@Cacheable(value = "globalLeaderboard",key = "#root.args[0]")
	public List<LeaderboardScore> topGlobal(Long gameId){
		Game game = gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not exist"));
		return scoreRepository.findByGameOrderByScoreDesc(game);
	}
	
	@Cacheable(value = "countryLeaderboard",key = "#root.args[0] + ':' + #root.args[1]")
	public List<LeaderboardScore> topCountry(Long gameId, String country){
	    Game game = gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not exist"));
		return scoreRepository.findByGameAndCountryOrderByScoreDesc(game, country);
	}
	
	
}
