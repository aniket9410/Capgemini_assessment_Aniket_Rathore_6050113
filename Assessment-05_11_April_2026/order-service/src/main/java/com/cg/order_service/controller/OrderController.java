package com.cg.order_service.controller;

import org.springframework.web.bind.annotation.*;

import com.cg.order_service.dto.OrderRequest;
import com.cg.order_service.dto.OrderResponse;
import com.cg.order_service.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;


    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        return service.createOrder(request);
    }
}