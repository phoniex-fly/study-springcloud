package com.example.feign;


import com.example.feign.hystrix.IOrderServiceFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "hystrix-provider", fallback = IOrderServiceFeignHystrix.class)
public interface IOrderServiceFeign {

    @GetMapping("/doOrder")
    String doOrder();
}
