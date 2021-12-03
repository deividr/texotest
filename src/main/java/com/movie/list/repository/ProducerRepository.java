package com.movie.list.repository;

import java.util.List;

import com.movie.list.model.Producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
  @Query("SELECT DISTINCT(p) FROM Producer p JOIN p.movies m WHERE m.isWinner = true")
  public List<Producer> findByWinnerMovie();

  public Producer findByName(String name);
}
