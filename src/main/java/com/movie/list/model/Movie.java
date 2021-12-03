package com.movie.list.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie implements Serializable, Comparable<Movie> {
  private static final long serialVersionUID = -8790876907908769087L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private Integer year;

  @Column
  private String title;

  @Column
  private String studios;

  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(name = "movies_producers", joinColumns = @JoinColumn(name = "producer_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
  @JsonIgnore
  private List<Producer> producers;

  @Column
  private Boolean isWinner;

  public Movie() {
  }

  public Movie(Integer year, String title, String studios, List<Producer> producers, Boolean isWinner) {
    setYear(year);
    setTitle(title);
    setStudios(studios);
    setProducers(producers);
    setIsWinner(isWinner);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStudios() {
    return studios;
  }

  public void setStudios(String studios) {
    this.studios = studios;
  }

  public List<Producer> getProducers() {
    return producers;
  }

  public void setProducers(List<Producer> producers) {
    this.producers = producers;
  }

  public Boolean isWinner() {
    return isWinner;
  }

  public void setIsWinner(Boolean isWinner) {
    this.isWinner = isWinner;
  }

  @Override
  public int compareTo(Movie o) {
    return o.getYear() - this.getYear();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Movie other = (Movie) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}