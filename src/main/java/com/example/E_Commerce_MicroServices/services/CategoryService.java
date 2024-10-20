package com.example.E_Commerce_MicroServices.services;

import com.example.E_Commerce_MicroServices.models.Category;
import com.example.E_Commerce_MicroServices.models.CategoryProjection;
import com.example.E_Commerce_MicroServices.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryProjection> getAllCategories() {
        return categoryRepository.findAllBy();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
