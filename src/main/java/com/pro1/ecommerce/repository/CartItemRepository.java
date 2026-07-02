package com.pro1.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro1.ecommerce.entity.Cart;
import com.pro1.ecommerce.entity.CartItem;
import com.pro1.ecommerce.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}