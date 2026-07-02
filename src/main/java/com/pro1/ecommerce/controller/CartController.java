package com.pro1.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

import com.pro1.ecommerce.dto.CartRequest;
import com.pro1.ecommerce.dto.CartResponse;
import com.pro1.ecommerce.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add Product To Cart
    @PostMapping("/add")
    public String addToCart(@Valid @RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    // View Cart
    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    // Remove Item
    @DeleteMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        return cartService.removeFromCart(id);
    }

    // Clear Cart
    @DeleteMapping("/clear")
    public String clearCart() {
        return cartService.clearCart();
    }
}