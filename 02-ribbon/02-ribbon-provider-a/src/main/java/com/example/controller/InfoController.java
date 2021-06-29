package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class InfoController {

    @GetMapping("info")
    public String info() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "第一个提供者";
    }

    @GetMapping("info1")
    public String info1() {
        return "第一个提供者";
    }
}
