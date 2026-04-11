package com.cg.api_gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .uri("lb://USER-SERVICE"))
                .route("product-service", r -> r.path("/products/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route("order-service", r -> r.path("/orders/**")
                        .uri("lb://ORDER-SERVICE"))
                .build();
    }
}
