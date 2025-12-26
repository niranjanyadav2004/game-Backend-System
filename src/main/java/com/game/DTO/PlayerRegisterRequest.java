package com.game.DTO;

import lombok.Getter;

@Getter
public class PlayerRegisterRequest {

	public String username;
	public String password;
	public String deviceId;
	public String platform;
	public String country;
	
}
