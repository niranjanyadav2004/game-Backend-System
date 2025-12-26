package com.game.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.game.DTO.GameRequest;
import com.game.entity.Game;
import com.game.repository.GameRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

	private final GameRepository gameRepository;
	
	public Game add(GameRequest request) {
		if(gameRepository.findByNameIgnoreCase(request.getName()).isPresent())
			     throw new EntityNotFoundException("Game is already present");
		
		Game game = new Game();
		game.setName(request.getName());
		game.setDescription(request.getDescription());
		game.setCreatedAt(LocalDateTime.now());
		
		return gameRepository.save(game);
	}
	
	public List<Game> getAllGames(){
		return gameRepository.findAll();
	}
	
	public Game getGameByName(String name) {
		Optional<Game> game = gameRepository.findByNameIgnoreCase(name);
		if(game.isEmpty()) throw new EntityNotFoundException(name + " is not present");
		
		return game.get();
	}
	
}
