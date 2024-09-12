package com.priyansu.shopping.category.controller;

import com.priyansu.shopping.category.model.dto.CategoryDto;
import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.service.CategoryServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Category Controller", description = "API for managing categories")
@Slf4j
@Validated
public class CategoryController {

    private final CategoryServiceInterface categoryServiceInterface;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryServiceInterface categoryServiceInterface, ModelMapper modelMapper) {
        this.categoryServiceInterface = categoryServiceInterface;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Create a new category", description = "This endpoint allows you to create a new category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "404", description = "Failed to create category")
    })
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            // copy values from category to categoryEntity -2nd method
            Category categoryEntity = modelMapper.map(categoryDto, Category.class);

            Category createdCategory  = categoryServiceInterface.createCategory(categoryEntity);

            CategoryDto responseDto = modelMapper.map(createdCategory, CategoryDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            log.error("***Failed to create category, source : CategoryController.java, createCategory()***");
            throw new RuntimeException("Failed to create category", e);
        }
    }

    @Operation(summary = "Get all categories", description = "Fetches the list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched the categories"),
            @ApiResponse(responseCode = "204", description = "No categories found")
    })
    @RequestMapping(value = "/getAllCategoryList", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        try {
            List<Category> categoryList = categoryServiceInterface.getAllCategory();
                // convert List<Category> to List<CategoryDto>
                List<CategoryDto> categoryDtoList = categoryList.stream()
                        .map(category -> modelMapper.map(category, CategoryDto.class))
                        .toList();

                return categoryDtoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categoryDtoList);
            }
        catch (Exception e) {
            log.error("***Failed to fetch all category list, source : CategoryController.java, getAllCategory()***", e);
            throw new RuntimeException("Failed to fetch all category list", e);
        }
    }

    @Operation(summary = "Update a category", description = "Updates a category based on the provided ID and data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CategoryDto categoryDto) {
        try {
            // copy values from categoryDto of *CategoryDto* type to category of *Category* type
            Category category = modelMapper.map(categoryDto, Category.class);

            Category updatedCategory = categoryServiceInterface.updateCategory(id, category);

            // copy values from updatedCategory of *Category* type to responseDto of *CategoryDto* type
            CategoryDto responseDto = modelMapper.map(updatedCategory, CategoryDto.class);

            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            log.error("***Failed to update category, source : CategoryController.java, updateCategory()***", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
