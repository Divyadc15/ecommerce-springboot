package com.pro1.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro1.ecommerce.entity.Cart;
import com.pro1.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

}