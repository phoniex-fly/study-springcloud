package com.example.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

class InfoControllerTest {
    RestTemplate restTemplate = null;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * getForObject 返回响应体
     * <p>
     * getForEntity 返回整个http响应对象
     */
    @Test
    void doGet() {
        String forObject = restTemplate.getForObject("http://localhost:9996/doGet?name=123", String.class);
        System.out.println(forObject);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9996/doGet?name=123", String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getStatusCodeValue());
        System.out.println(forEntity.getHeaders().getContentLength());
        System.out.println(forEntity.getBody());
    }

    @Test
    void doPostParam() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("name", "zhangsan");
        map.add("age", "18");
        map.add("gender", "F");
        String forObject = restTemplate.postForObject("http://localhost:9996/doPostParam", map, String.class);
        System.out.println(forObject);
    }

    @Test
    void doPostJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "18");
        map.put("gender", "F");
        String forObject = restTemplate.postForObject("http://localhost:9996/doPostJson", map, String.class);
        System.out.println(forObject);
    }
}
