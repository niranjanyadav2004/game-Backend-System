package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.GameEvent;
import com.game.status.EventStatus;

public interface GameEventRepository extends JpaRepository<GameEvent, Long> {

	List<GameEvent> findByStatus(EventStatus status);
	
}
