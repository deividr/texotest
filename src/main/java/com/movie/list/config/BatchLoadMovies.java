package com.movie.list.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.movie.list.model.Movie;
import com.movie.list.model.Producer;
import com.movie.list.repository.MovieRepository;
import com.movie.list.repository.ProducerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchLoadMovies {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private ProducerRepository producerRepository;

  @Bean
  public void load() {
    List<List<String>> records = new ArrayList<>();
    InputStream in = getClass().getResourceAsStream("/movielist.csv");

    if (Objects.isNull(in)) {
      throw new IllegalArgumentException("File movielist.csv not found");
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(";");
        records.add(Arrays.asList(values));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // For each record, create a movie object and save it to the database
    records.forEach(record -> {
      if (record.get(0).contains("year")) {
        return;
      }

      Boolean isWinner = record.size() == 5 ? true : false;

      List<Producer> producers = Arrays.asList(record.get(3).split(", | and ")).stream().map(String::trim)
          .map(name -> name.replace("and ", ""))
          .map(producer -> {
            Producer producerObj = producerRepository.findByName(producer);

            if (producerObj == null) {
              producerObj = new Producer(producer);
              producerRepository.save(producerObj);
            }

            return producerObj;
          })
          .collect(java.util.stream.Collectors.toList());

      Movie movie = new Movie(Integer.parseInt(record.get(0)), record.get(1), record.get(2),
          producers,
          isWinner);

      movieRepository.save(movie);
    });
  }
}
