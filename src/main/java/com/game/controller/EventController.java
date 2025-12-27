package com.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.DTO.EventRequest;
import com.game.DTO.EventUpdateRequest;
import com.game.service.EventService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody EventRequest request){
		try {
			return ResponseEntity.ok(eventService.create(request));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@PutMapping("/{eventId}")
	public ResponseEntity<?> update(@PathVariable(name = "eventId") Long eventId, @RequestBody EventUpdateRequest request){
		try {
			return ResponseEntity.ok(eventService.update(eventId,request));
		}
		catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveEvents(){
		try {
			return ResponseEntity.ok(eventService.activeEvents());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEvents(){
		try {
			return ResponseEntity.ok(eventService.allEvents());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/{eventId}")
	public ResponseEntity<?> getEventById(@PathVariable(name = "eventId") Long eventId){
		try {
			return ResponseEntity.ok(eventService.getEventById(eventId));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
