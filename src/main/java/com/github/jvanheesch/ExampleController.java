package com.github.jvanheesch;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@RestController
public class ExampleController {
    private final RestTemplate restTemplate;

    public ExampleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/example")
    public void uuidBoot() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        RequestEntity<String> requestUuid = new RequestEntity<>(
                headers,
                HttpMethod.GET,
                URI.create("http://localhost:8080/uuid/uuid")
        );
        RequestEntity<String> requestString = new RequestEntity<>(
                headers,
                HttpMethod.GET,
                URI.create("http://localhost:8080/uuid/string")
        );

        ResponseEntity<UUID> exchangeUuid = restTemplate.exchange(requestUuid, UUID.class);
        ResponseEntity<String> exchangeStringOk = restTemplate.exchange(requestString, String.class);
        ResponseEntity<UUID> exchangeStringNok = restTemplate.exchange(requestString, UUID.class);
    }
}
