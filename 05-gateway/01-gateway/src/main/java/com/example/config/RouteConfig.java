package com.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件形式  不常用
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("guoji-service", r -> r.path("/guoji").uri("http://news.baidu.com/"))
                .route("finance-service", r -> r.path("/finance").uri("http://news.baidu.com/"))
                .route("auto-service", r -> r.path("/auto").uri("http://news.baidu.com/"));
        return routes.build();
    }
}
