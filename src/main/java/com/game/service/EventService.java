package com.game.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.game.DTO.EventRequest;
import com.game.DTO.EventUpdateRequest;
import com.game.entity.Game;
import com.game.entity.GameEvent;
import com.game.repository.GameEventRepository;
import com.game.repository.GameRepository;
import com.game.status.EventStatus;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final GameRepository gameRepository;
	private final GameEventRepository gameEventRepository;
	
	@CacheEvict(value = {"activeEvents","allEvents"},allEntries = true)
	public GameEvent create(EventRequest request) {
		Game game = gameRepository.findById(request.getGameId()).orElseThrow(()->new EntityNotFoundException("Game not found"));
		
		GameEvent gameEvent = new GameEvent();
		gameEvent.setGame(game);
		gameEvent.setName(request.getName());
		gameEvent.setStartTime(request.getStartTime());
		gameEvent.setEndTime(request.getEndTime());
		gameEvent.setStatus(EventStatus.SCHEDULED);
		gameEvent.setEventConfig(request.getEventConfig());
		gameEvent.setCreatedAt(LocalDateTime.now());
		
		return gameEventRepository.save(gameEvent);
	}
	
	
	@CacheEvict(value = {"activeEvents","allEvents"},allEntries = true)
	public GameEvent update(Long eventId, EventUpdateRequest request) {
		  Game game = gameRepository.findById(request.getGameId()).orElseThrow(()->new EntityNotFoundException("Game not found"));
		  GameEvent event = gameEventRepository.findById(eventId).orElseThrow(()->new EntityNotFoundException("Event Not found"));
		  
		  if(request.getGameId()!=null) event.setGame(game);
		  if(request.getName()!=null) event.setName(request.getName());
		  if(request.getStartTime()!=null) event.setStartTime(request.getStartTime());
		  if(request.getEndTime()!=null) event.setEndTime(request.getEndTime());
		  if(request.getEventConfig()!=null) event.setEventConfig(request.getEventConfig());
		  
		  return gameEventRepository.save(event);
	}
	
	
	public List<GameEvent> activeEvents(){
		System.out.println("Get active events from db");
		return gameEventRepository.findByStatus(EventStatus.ACTIVE);
	}
	
	public List<GameEvent> allEvents(){
		System.out.println("Get all events from db");
		return gameEventRepository.findAll();
	}
	

	public GameEvent getEventById(Long eventId) {
		System.out.println("Get eventId from db");
		return gameEventRepository.findById(eventId).orElseThrow(()->new EntityNotFoundException("Event Not found"));
	}
	
}
