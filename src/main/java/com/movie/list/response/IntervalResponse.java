package com.movie.list.response;

import java.io.Serializable;
import java.util.List;

public class IntervalResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<ProducerInterval> max;

  private List<ProducerInterval> min;

  public IntervalResponse(List<ProducerInterval> min, List<ProducerInterval> max) {
    this.min = min;
    this.max = max;
  }

  public List<ProducerInterval> getMax() {
    return max;
  }

  public void setMax(List<ProducerInterval> max) {
    this.max = max;
  }

  public List<ProducerInterval> getMin() {
    return min;
  }

  public void setMin(List<ProducerInterval> min) {
    this.min = min;
  }

}
