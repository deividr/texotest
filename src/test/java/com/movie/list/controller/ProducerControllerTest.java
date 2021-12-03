package com.movie.list.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.movie.list.response.IntervalResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProducerControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private ProducerController producerController;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void contextLoads() throws Exception {
    assertNotNull(producerController);
  }

  @Test
  void testGetProducers() throws Exception {
    ResponseEntity<IntervalResponse> response = restTemplate.getForEntity("http://localhost:" + port + "/producers",
        IntervalResponse.class);

    assertTrue(response.getStatusCode() == HttpStatus.OK);

    assertTrue(response.getBody().getMax().get(0).getProducer().equals("Matthew Vaughn"));
    assertTrue(response.getBody().getMax().get(0).getInterval().equals(13));
    assertTrue(response.getBody().getMax().get(0).getPreviousWin().equals(2002));
    assertTrue(response.getBody().getMax().get(0).getFollowingWin().equals(2015));

    assertTrue(response.getBody().getMin().get(0).getProducer().equals("Joel Silver"));
    assertTrue(response.getBody().getMin().get(0).getInterval().equals(1));
    assertTrue(response.getBody().getMin().get(0).getPreviousWin().equals(1990));
    assertTrue(response.getBody().getMin().get(0).getFollowingWin().equals(1991));
  }

}
