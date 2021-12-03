package com.movie.list.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.movie.list.model.Movie;
import com.movie.list.model.Producer;
import com.movie.list.repository.ProducerRepository;
import com.movie.list.response.IntervalResponse;
import com.movie.list.response.ProducerInterval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
public class ProducerController {
  @Autowired
  private ProducerRepository producerRepository;

  @GetMapping
  public ResponseEntity<IntervalResponse> getProducers() {
    List<Producer> producers = producerRepository.findByWinnerMovie();

    // Remove movies that have no winner
    producers.forEach(producer -> {
      producer.setMovies(producer.getMovies().stream().filter(movie -> movie.isWinner()).collect(Collectors.toList()));
    });

    // Remove producers that have just one winner movie
    producers = producers.stream().filter(producer -> producer.getMovies().size() > 1).collect(
        Collectors.toList());

    // Sorted movies by descending order of year
    producers.forEach(producer -> {
      producer.setMovies(producer.getMovies().stream().sorted().collect(Collectors.toList()));
    });

    List<ProducerInterval> producerIntervals = new ArrayList<>();

    // Get the interval of years for each producer
    producers.forEach(producer -> {
      List<Movie> movies = producer.getMovies();
      Integer interval = movies.get(0).getYear() - movies.get(1).getYear();

      producerIntervals
          .add(new ProducerInterval(producer.getName(), interval, movies.get(1).getYear(), movies.get(0).getYear()));
    });

    // Get max and min interval
    Integer maxInterval = producerIntervals.stream().map(ProducerInterval::getInterval).max(Integer::compareTo).get();
    Integer minInterval = producerIntervals.stream().map(ProducerInterval::getInterval).min(Integer::compareTo).get();

    // Get producers with max interval year
    List<ProducerInterval> maxProducers = producerIntervals.stream()
        .filter(producerInterval -> producerInterval.getInterval().equals(maxInterval)).collect(Collectors.toList());

    // Get producers with min interval year
    List<ProducerInterval> minProducers = producerIntervals.stream()
        .filter(producerInterval -> producerInterval.getInterval().equals(minInterval)).collect(Collectors.toList());

    return ResponseEntity.ok(new IntervalResponse(minProducers, maxProducers));
  }
}
