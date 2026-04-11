package com.cg.order_service.service;

import org.springframework.stereotype.Service;

import com.cg.order_service.client.ProductClient;
import com.cg.order_service.client.UserClient;
import com.cg.order_service.dto.OrderRequest;
import com.cg.order_service.dto.OrderResponse;
import com.cg.order_service.dto.ProductDTO;
import com.cg.order_service.dto.UserDTO;
import com.cg.order_service.exception.ProductNotFoundException;
import com.cg.order_service.exception.UserNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderService(UserClient userClient, ProductClient productClient) {
        this.userClient = userClient;
        this.productClient = productClient;
    }

    @CircuitBreaker(name = "orderService", fallbackMethod = "fallback")
    public OrderResponse createOrder(OrderRequest request) {

        UserDTO user = userClient.getUser(request.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + request.getUserId());
        }

        ProductDTO product = productClient.getProduct(request.getProductId());
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + request.getProductId());
        }

        double totalPrice = product.getPrice() * request.getQuantity();

        return new OrderResponse(
                generateOrderId(),
                user.getName(),
                product.getName(),
                request.getQuantity(),
                totalPrice
        );
    }

    private Long generateOrderId() {
        return System.currentTimeMillis();
    }


    public OrderResponse fallback(OrderRequest request, Throwable t) {
        return new OrderResponse(
                0L,
                "Service Down",
                "Service Down",
                request.getQuantity(),
                0
        );
    }
}