package com.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.DTO.ProgressionRequest;
import com.game.service.PlayerProgressionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progression")
@RequiredArgsConstructor
public class PlayerProgressionController {

	private final PlayerProgressionService playerProgressionService;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProgressionRequest request){
		try {
			return ResponseEntity.ok(playerProgressionService.saveProgress(request));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/{playerId}")
	public ResponseEntity<?> getPlayerProgression(@PathVariable(name = "playerId") Long playerId){
		try {
			return ResponseEntity.ok(playerProgressionService.getPlayerProgress(playerId));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
