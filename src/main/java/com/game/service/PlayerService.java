package com.game.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.game.DTO.PlayerRegisterRequest;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

	
	private final PlayerRepository playerRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Player register(PlayerRegisterRequest request) {
		if(playerRepository.findByDeviceId(request.getDeviceId()).isPresent()) 
			   throw new EntityNotFoundException("Player already existed");
		
		Player player = new Player();
		player.setUsername(request.getUsername());
		player.setPassword(passwordEncoder.encode(request.getPassword()));
		player.setDeviceId(request.getDeviceId());
		player.setPlatform(request.getPlatform());
		player.setCountry(request.getCountry());
		player.setCreatedAt(LocalDateTime.now());
		player.setLastActiveAt(LocalDateTime.now());
		
		return playerRepository.save(player);
	}
	
	
}
