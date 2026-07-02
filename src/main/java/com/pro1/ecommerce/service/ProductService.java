package com.pro1.ecommerce.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pro1.ecommerce.dto.ProductRequest;
import com.pro1.ecommerce.entity.Category;
import com.pro1.ecommerce.entity.Product;
import com.pro1.ecommerce.repository.CategoryRepository;
import com.pro1.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Add Product
    public String addProduct(ProductRequest request) {

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return "Category Not Found";
        }

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        productRepository.save(product);

        return "Product Added Successfully";
    }

    // Get All Products
 // Get All Products With Pagination & Sorting
    public Page<Product> getAllProducts(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending());

        return productRepository.findAll(pageable);
    }

    // Get Product By Id
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElse(null);
    }

    // Update Product
    public String updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElse(null);

        if (product == null) {
            return "Product Not Found";
        }

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return "Category Not Found";
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        productRepository.save(product);

        return "Product Updated Successfully";
    }

    // Delete Product
    public String deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            return "Product Not Found";
        }

        productRepository.deleteById(id);

        return "Product Deleted Successfully";
    }

    // Get Products By Category
    public List<Product> getProductsByCategory(Long categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElse(null);

        if (category == null) {
            return List.of();
        }

        return productRepository.findByCategory(category);
    }
 // Search Products
    public List<Product> searchProducts(String keyword) {

        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}