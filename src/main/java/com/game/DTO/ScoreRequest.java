package com.game.DTO;

import lombok.Getter;

@Getter
public class ScoreRequest {

	public Long playerId;
	public Long gameId;
	public int score;
	
}
