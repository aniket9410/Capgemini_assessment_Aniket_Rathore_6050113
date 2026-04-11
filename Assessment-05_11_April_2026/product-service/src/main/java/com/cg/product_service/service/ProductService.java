package com.cg.product_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cg.product_service.entity.Product;
import com.cg.product_service.exception.ProductNotFoundException;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductService() {
        products.put(101L, new Product(101L, "Keyboard", 1200));
    }

    public Product getProduct(Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        return product;
    }
}
