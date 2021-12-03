package com.movie.list.controller;

import java.util.List;

import com.movie.list.model.Movie;
import com.movie.list.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieRepository movieRepository;

  @GetMapping
  public List<Movie> list() {
    List<Movie> movies = movieRepository.findAll();
    return movies;
  }

  @PostMapping
  public Movie create(@RequestBody Movie movie) {
    return movieRepository.save(movie);
  }
}
