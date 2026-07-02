package com.pro1.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pro1.ecommerce.dto.OrderResponse;
import com.pro1.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place Order
    @PostMapping
    public String placeOrder() {
        return orderService.placeOrder();
    }

    // Get All Orders
    @GetMapping
    public List<OrderResponse> getMyOrders() {
        return orderService.getMyOrders();
    }

    // Get Order By Id
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Cancel Order
    @PutMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }
}