//package com.example.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * token校验过滤器
// */
//@Configuration
//public class TokenFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        List<String> tokens = request.getHeaders().get("TOKEN");
//        if (!CollectionUtils.isEmpty(tokens)) {
//            String token = tokens.get(0);
//            if (!StringUtils.isEmpty(token)) {
//                return chain.filter(exchange);
//            }
//        }
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        response.getHeaders().add("content-type", "application/json;charset=utf-8");
//        Map<String, String> map = new HashMap<>(4);
//        map.put("code", "401");
//        map.put("msg", "未授权");
//
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] bytes = new byte[0];
//        try {
//            bytes = mapper.writeValueAsBytes(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        DataBuffer wrap = response.bufferFactory().wrap(bytes);
//        return response.writeWith(Mono.just(wrap));
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
