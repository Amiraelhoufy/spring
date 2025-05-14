package org.agcodes.restconsumer.controller;

import java.util.List;
import org.agcodes.restconsumer.model.Contact;
import org.agcodes.restconsumer.model.Response;
import org.agcodes.restconsumer.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ContactController {

  @Autowired
  ContactProxy contactProxy;
  @Autowired
  RestTemplate restTemplate;
  @Autowired
  WebClient webClient;


  // Using OpenFeign
  @GetMapping("/getMessages")
  public List<Contact> getMessages(@RequestParam("status") String status){
    return contactProxy.getMessagesByStatus(status);
  }

// Using REST-Template
  /*
  @PostMapping("/saveMsg")
  public ResponseEntity<Response> saveMsg(@RequestBody Contact contact){
    String uri = "http://localhost:8080/api/v1/contacts/saveMsg";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("invocationFrom","RestTemplate");

    HttpEntity<Contact> httpEntity = new HttpEntity<>(contact,httpHeaders);
    ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
        httpEntity, Response.class);
    return responseEntity;
  }
  */

  // Using WebClient
  @PostMapping("/saveMsg")
  public Mono<Response> saveMsg(@RequestBody Contact contact){
    String uri = "http://localhost:8080/api/v1/contacts/saveMsg";
    return webClient.post().uri(uri)
        .header("invocationFrom","WebClient")
        .body(Mono.just(contact),Contact.class)
        .retrieve()
        .bodyToMono(Response.class);
  }

}
