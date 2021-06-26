package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/doOrder")
    public String doOrder() {
        String forObject = restTemplate.getForObject("http://localhost:9992/doOrder", String.class);
        return "ok";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
