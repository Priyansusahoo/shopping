package com.priyansu.shopping.category.controller;

import com.google.gson.Gson;
import com.priyansu.shopping.category.model.dto.CategoryDto;
import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.service.CategoryServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryServiceInterface categoryServiceInterface;

    @Autowired
    private Gson gson;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createCategory")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            // copy values from category to categoryEntity -2nd method
            Category categoryEntity = modelMapper.map(categoryDto, Category.class);

            boolean response = categoryServiceInterface.createCategory(categoryEntity);
            if (response) {
                return ResponseEntity.ok("Category created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create category");
            }
        } catch (Exception e) {
            log.error("***Failed to create category, source : CategoryController.java, createCategory()***");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllCategoryList")
    public ResponseEntity<String> getAllCategory() {
        try {
            List<Category> categoryList = categoryServiceInterface.getAllCategory();
            if (categoryList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Category found!");
            } else {
                // convert List<Category> to List<CategoryDto>
                List<CategoryDto> categoryDtoList = categoryList.stream()
                        .map(category -> modelMapper.map(category, CategoryDto.class))
                        .toList();

                String categoryDtoListJson = gson.toJson(categoryDtoList);
                return ResponseEntity.ok(categoryDtoListJson);
            }
        } catch (Exception e) {
            log.error("***Failed to fetch all category list, source : CategoryController.java, getAllCategory()***");
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        try {
            // copy values from categoryDto of *CategoryDto* type to category of *Category* type
            Category category = modelMapper.map(categoryDto, Category.class);

            Category updatedCategory = categoryServiceInterface.updateCategory(id, category);

            // copy values from updatedCategory of *Category* type to reponseCategoryDto of *CategoryDto* type
            CategoryDto reponseCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);

            return ResponseEntity.ok(gson.toJson(reponseCategoryDto));
        } catch (RuntimeException e) {
            log.error("***Failed to update category, source : CategoryController.java, updateCategory()***");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " not found");
        }
    }
}
