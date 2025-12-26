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
@Table(name = "scores")
@Data
public class LeaderboardScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "score_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "player_id",nullable = false)
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "game_id",nullable = false)
	private Game game;
	
	private int score;
	private String country;
	private LocalDateTime playeAt;
	
}
