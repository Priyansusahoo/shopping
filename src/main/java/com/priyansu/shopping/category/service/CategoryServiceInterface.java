package com.priyansu.shopping.category.service;

import com.priyansu.shopping.category.model.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {
    boolean createCategory(Category category);

    List<Category> getAllCategory();

    Category updateCategory(Long id, Category category);
}
