package com.pro1.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pro1.ecommerce.dto.CategoryRequest;
import com.pro1.ecommerce.entity.Category;
import com.pro1.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Add Category
    public String addCategory(CategoryRequest request) {

        if (categoryRepository.findByName(request.getName()).isPresent()) {
            return "Category already exists";
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);

        return "Category Added Successfully";
    }

    // Get All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get Category By Id
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElse(null);
    }

    // Update Category
    public String updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElse(null);

        if (category == null) {
            return "Category Not Found";
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);

        return "Category Updated Successfully";
    }

    // Delete Category
    public String deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {
            return "Category Not Found";
        }

        categoryRepository.deleteById(id);

        return "Category Deleted Successfully";
    }
}