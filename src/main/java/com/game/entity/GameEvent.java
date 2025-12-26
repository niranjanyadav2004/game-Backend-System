package com.game.entity;

import java.time.LocalDateTime;

import com.game.status.EventStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "game_events")
@Data
public class GameEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "game_id",nullable = false)
	private Game game;
	
	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	
	@Column(columnDefinition = "json")
    private String eventConfig;

    private LocalDateTime createdAt;
	
}
