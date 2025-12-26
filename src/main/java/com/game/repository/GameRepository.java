package com.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.Game;


public interface GameRepository extends JpaRepository<Game, Long> {
      Optional<Game> findByNameIgnoreCase(String name);
}
