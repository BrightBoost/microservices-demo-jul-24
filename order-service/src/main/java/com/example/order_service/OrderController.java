package com.example.order_service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private ProductClient productClient;
    public OrderController(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Product product = productClient.getProductById(order.getProductId());
        if(order.getQuantity() < product.getStock()) {
            return orderRepository.save(order);
        } else {
            throw new IllegalStateException("this is quantity is not in stock!");
        }
    }
}
