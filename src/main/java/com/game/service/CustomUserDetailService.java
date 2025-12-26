package com.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player player = playerRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found...!!!"));
		return player;
	}

}
