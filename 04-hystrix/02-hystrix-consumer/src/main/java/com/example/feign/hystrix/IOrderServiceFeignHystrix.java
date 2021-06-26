package com.example.feign.hystrix;


import com.example.feign.IOrderServiceFeign;
import org.springframework.stereotype.Component;

/**
 * 实现方式简单，但是获取不到HTTP请求错误状态码和信息
 */
@Component
public class IOrderServiceFeignHystrix implements IOrderServiceFeign {


    @Override
    public String doOrder() {
        return "我是备胎";
    }
}
