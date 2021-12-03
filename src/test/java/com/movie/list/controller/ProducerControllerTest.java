package com.movie.list.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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
    String result = restTemplate.getForObject("http://localhost:" + port + "/producers", String.class);
    assertTrue(result.contains("max"));
    assertTrue(result.contains("min"));
  }

}
