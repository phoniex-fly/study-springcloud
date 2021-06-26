package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
public class LBController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 自定义负载均衡
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/getInfo")
    public String getInfo(String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        ServiceInstance instance = myLoadBalance(instances);
        String forObject = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/info", String.class);
        return forObject;
    }

    private ServiceInstance myLoadBalance(List<ServiceInstance> instances) {
        int i = new Random().nextInt(instances.size());
        return instances.get(i);
    }

    /**
     * 在restTemplate()上加@LoadBalanced 让RestTemplate被ribbon管理
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/getInfoByRibbon")
    public String getInfoByRibbon(String serviceId) {
        String forObject = restTemplate.getForObject("http://" + serviceId + "/info", String.class);
        return forObject;
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
