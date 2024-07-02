package com.example.order_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "product-service", url="${product.service.url}")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
