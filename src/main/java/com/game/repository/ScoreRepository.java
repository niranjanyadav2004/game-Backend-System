package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.Game;
import com.game.entity.LeaderboardScore;

public interface ScoreRepository extends JpaRepository<LeaderboardScore, Long> {

    List<LeaderboardScore> findByGameOrderByScoreDesc(Game game);

    List<LeaderboardScore> findByGameAndCountryOrderByScoreDesc(Game game, String country);
}

