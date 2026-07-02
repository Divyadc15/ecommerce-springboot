package com.pro1.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro1.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}