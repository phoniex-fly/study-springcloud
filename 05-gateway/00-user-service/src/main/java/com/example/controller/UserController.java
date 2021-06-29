package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/userinfo")
    public String user() {
        return "{user:zhangsan,age:14}";
    }
}
