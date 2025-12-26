package com.game.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "player_progression")
@Data
public class PlayerProgression {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "progress_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "player_id",nullable = false)
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;
	
	 private int level;
	 
	 @Column(name = "player_rank")
	 private int rank;

	 private int gold;
	 private int cash;
	 private int gem;

	 @Column(columnDefinition = "json")
	 private String rewards;
	
	 private LocalDateTime lastActiveAt;
	 private LocalDateTime updatedAt;
	 
}
