package com.pro1.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro1.ecommerce.entity.Category;
import com.pro1.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
    List<Product> findByNameContainingIgnoreCase(String keyword);
    
}