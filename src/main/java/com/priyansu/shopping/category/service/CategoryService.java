package com.priyansu.shopping.category.service;

import com.priyansu.shopping.category.exception.CategoryNotFoundException;
import com.priyansu.shopping.category.exception.CategoryServiceException;
import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.model.jparepository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Slf4j
public class CategoryService implements CategoryServiceInterface{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (DataAccessException e) {
            log.error("Database error while creating category", e);
            throw new CategoryServiceException("Error creating category", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
            return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {
        try {
            Category existingCategory  = categoryRepository.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
            // Mapping ---START---
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setImageUrl(category.getImageUrl());
            existingCategory.setDescription(category.getDescription());
            // Mapping ---END---

            return categoryRepository.save(existingCategory);
        } catch (Exception e) {
            log.error("Error updating category with id {} in updateCategory()", id, e);
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
    }
}
