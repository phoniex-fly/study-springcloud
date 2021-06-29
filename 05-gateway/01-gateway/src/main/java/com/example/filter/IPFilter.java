//package com.example.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class IPFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String ip = request.getHeaders().getHost().getHostName();
//        if (StringUtils.isEmpty(ip) || !ip.equals("localhost")) {
//            return chain.filter(exchange);
//        }
//        ServerHttpResponse response = exchange.getResponse();
//        response.getHeaders().add("content-type", "application/json;charset=utf-8");
//        Map<String, String> map = new HashMap<>(4);
//        map.put("code", "401");
//        map.put("msg", "黑名单用户");
//
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] bytes = new byte[0];
//        try {
//            bytes = mapper.writeValueAsBytes(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        DataBuffer wrap = response.bufferFactory().wrap(bytes);
//        return response.writeWith(Mono.just(wrap));
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
