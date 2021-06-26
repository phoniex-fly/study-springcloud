package com.example.controller;

import com.example.feign.IOrderServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IOrderServiceFeign iOrderServiceFeign;

    @GetMapping("doOrder")
    public String doOrder() {
        String s = iOrderServiceFeign.doOrder();
        return s;
    }
}

