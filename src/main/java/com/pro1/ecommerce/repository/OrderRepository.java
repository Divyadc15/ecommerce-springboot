package com.pro1.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro1.ecommerce.entity.Order;
import com.pro1.ecommerce.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    Optional<Order> findByIdAndUser(Long id, User user);
}