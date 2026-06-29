package com.pro1.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pro1.ecommerce.dto.ProductRequest;
import com.pro1.ecommerce.entity.Product;
import com.pro1.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add Product
    @PostMapping
    public String addProduct(@Valid @RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Product By Id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Update Product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    // Get Products By Category
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }
}