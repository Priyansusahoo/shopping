package com.priyansu.shopping.category.service;

import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.model.jparepository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService implements CategoryServiceInterface{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean createCategory(Category category) {
        try {
            Category category1 = categoryRepository.save(category);
            return category1.getId() != null;
        } catch (Exception e) {
            log.error("***Error while executing : 'CategoryService.java', 'createCategory(Category category)' method***");
            throw new RuntimeException("Error creating category", e);
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            log.error("***Error while executing : 'getAllCategory()' in 'CategoryService'***");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        try {
            Optional<Category> category1 = categoryRepository.findById(id);

            if (category1.isPresent()) {
                Category category2 = category1.get();
                category2.setCategoryName(category.getCategoryName());
                category2.setImageUrl(category.getImageUrl());
                category2.setDescription(category.getDescription());

                return categoryRepository.save(category2);
            } else {
                log.error("***Error while executing 'CategoryService.java', 'updateCategory(Long id, Category category)' method***");
                throw new RuntimeException("Category with id = " + category.getId() + "not found");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
