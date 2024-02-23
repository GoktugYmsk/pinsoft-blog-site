package com.pinsoft.blogsite.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GraphQLController {
  @PostMapping(value = "/graphql", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> graphql(@RequestBody String query) {

    String result = performGraphQLQuery(query);
    return ResponseEntity.ok().body(result);
  }

  private String performGraphQLQuery(String query) {

    return "GraphQL query result for: " + query;
  }
}
