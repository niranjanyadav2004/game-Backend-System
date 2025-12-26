package com.game.DTO;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class EventRequest {

	 public Long gameId;
	 public String name;
	 public LocalDateTime startTime;
	 public LocalDateTime endTime;
	 public String eventConfig;
	
}
