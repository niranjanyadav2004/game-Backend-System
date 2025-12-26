package com.game.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.entity.Player;
import com.game.entity.RefreshToken;
import com.game.repository.PlayerRepository;
import com.game.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
	
	private long refreshTokenValidity = 10*60*60*1000;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public RefreshToken createRefreshToken(String userName){
		Player player = playerRepository.findByUsername(userName).get();
		
		RefreshToken refreshToken1 = player.getRefreshToken();

		if(refreshToken1==null) {
			 refreshToken1=RefreshToken.builder()
			                           .refresToken(UUID.randomUUID().toString())
			                           .expiry(Instant.now().plusMillis(refreshTokenValidity))
			                           .player(player)
			                           .build();
		}
		else {
			refreshToken1.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
		
		player.setRefreshToken(refreshToken1);
		
	    refreshTokenRepository.save(refreshToken1);
		
		return refreshToken1;
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) {
		RefreshToken refreshTokenOb = refreshTokenRepository.findByRefresToken(refreshToken).orElseThrow(()-> new RuntimeException("Token does not exist..!!!"));
		
		if(refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenOb);
			throw new RuntimeException("Refresh token expired..!!!");
		}

        return refreshTokenOb;
	}
	
}
