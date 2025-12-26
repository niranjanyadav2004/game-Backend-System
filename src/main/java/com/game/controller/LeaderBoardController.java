package com.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.DTO.ScoreRequest;
import com.game.service.LeaderBoardService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderBoardController {

	private final LeaderBoardService leaderBoardService;
	
	@PostMapping("/submit")
	public ResponseEntity<?> submit(@RequestBody ScoreRequest request) {
		try {
			return ResponseEntity.ok(leaderBoardService.submit(request));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/top/global/{gameId}")
	public ResponseEntity<?> topGlobal(@PathVariable(name = "gameId") Long gameId){
		try {
			return ResponseEntity.ok(leaderBoardService.topGlobal(gameId));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/top/country/{gameId}")
	public ResponseEntity<?> topCountry(@PathVariable(name = "gameId") Long gameId, @RequestParam(name = "country") String country){
		try {
			return ResponseEntity.ok(leaderBoardService.topCountry(gameId,country));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	
}
