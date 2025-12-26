package com.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.DTO.GameRequest;
import com.game.service.GameService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

	private final GameService gameService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody GameRequest request){
		try {
			return ResponseEntity.ok(gameService.add(request));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllGames(){
		return ResponseEntity.ok(gameService.getAllGames());
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> getGameByName(@PathVariable(name = "name") String name){
		try {
			return ResponseEntity.ok(gameService.getGameByName(name));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
}
