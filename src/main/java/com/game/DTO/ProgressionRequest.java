package com.game.DTO;

import lombok.Getter;

@Getter
public class ProgressionRequest {

	public Long playerId;
    public Long gameId;

    public int level;
    public int rank;

    public int gold;
    public int cash;
    public int gem;

    public String rewards;
    
}
