package com.priyansu.shopping.category.controller;

import com.google.gson.Gson;
import com.priyansu.shopping.category.model.dto.CategoryDto;
import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.service.CategoryService;
import com.priyansu.shopping.category.service.CategoryServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryServiceInterface categoryServiceInterface;

    @Autowired
    private Gson gson;

    @PostMapping("/createCategory")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto category) {
        // copy values from category to categoryEntity
        Category categoryEntity = Category.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl()).build();

        boolean response = categoryServiceInterface.createCategory(categoryEntity);
        if (response) {
            return ResponseEntity.ok("Category created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create category");
        }
    }

    @GetMapping("/getAllCategoryList")
    public ResponseEntity<String> getAllCategory() {
        List<Category> CatList = categoryServiceInterface.getAllCategory();
        if (CatList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Category found!");
        } else {
            // convert List<Category> to List<CategoryDto>
            List<CategoryDto> categoryDtoList = CatList.stream()
                    .map(category -> new CategoryDto(
                            category.getId(),
                            category.getCategoryName(),
                            category.getDescription(),
                            category.getImageUrl()
                    ))
                    .toList();

            String categoryDtoListJson = gson.toJson(categoryDtoList);
            return ResponseEntity.ok(categoryDtoListJson);
        }
    }

    @PostMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        try {
            // copy values from categoryDto of *CategoryDto* type to category of *Category* type
            Category category = Category.builder()
                    .categoryName(categoryDto.getCategoryName())
                    .description(categoryDto.getDescription())
                    .imageUrl(categoryDto.getImageUrl())
                    .build();

            Category updatedCategory = categoryServiceInterface.updateCategory(id, category);

            // copy values from updatedCategory of *Category* type to reponseCategoryDto of *CategoryDto* type
            CategoryDto reponseCategoryDto = CategoryDto.builder()
                    .id(updatedCategory.getId())
                    .categoryName(updatedCategory.getCategoryName())
                    .description(updatedCategory.getDescription())
                    .imageUrl(updatedCategory.getImageUrl())
                    .build();
            return ResponseEntity.ok(gson.toJson(reponseCategoryDto));
        } catch (RuntimeException e) {
            log.error("Failed to update category, source : CategoryController.java, updateCategory()");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " not found");
        }
    }
}
