package com.game.schedular;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.game.entity.GameEvent;
import com.game.repository.GameEventRepository;
import com.game.status.EventStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventStatusScheduler {

	private final GameEventRepository gameEventRepository;
	
	@Scheduled(fixedRate = 30000)
	public void updateEventStatus() {
		LocalDateTime now = LocalDateTime.now();
		List<GameEvent> events = gameEventRepository.findAll();
		
		for(GameEvent event:events) {
			if(now.isBefore(event.getStartTime())) event.setStatus(EventStatus.SCHEDULED);
			else if(now.isAfter(event.getEndTime())) event.setStatus(EventStatus.FINISHED);
			else event.setStatus(EventStatus.ACTIVE);
		}
		
		gameEventRepository.saveAll(events);
	}
	
}
