package com.example.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InfoController {

    @GetMapping("/doGet")
    public String doGet(String name){
        System.out.println(name);
        return "访问doGet成功";
    }

    /**
     * 表单参数
     *
     * @param param
     * @return
     */
    @PostMapping("/doPostParam")
    public String doPostParam(@RequestParam Map<String,String> param){
        System.out.println(param);
        return "访问doPostParam成功";
    }

    /**
     *
     * @param param
     * @return
     */
    @PostMapping("/doPostJson")
    public String doPostJson(@RequestBody Map<String,String> param){
        System.out.println(param);
        return "访问doPostJson成功";
    }
}
