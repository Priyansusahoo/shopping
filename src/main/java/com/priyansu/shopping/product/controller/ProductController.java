package com.priyansu.shopping.product.controller;

import com.google.gson.Gson;
import com.priyansu.shopping.category.model.dto.CategoryDto;
import com.priyansu.shopping.product.model.dto.ProductDto;
import com.priyansu.shopping.product.service.ProductServiceInterf;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product Controller", description = "API for managing product")
@Slf4j
@Validated
public class ProductController {

    private final ProductServiceInterf productServiceInterf;

    private final Gson gson;

    @Autowired
    ProductController(ProductServiceInterf productServiceInterf, Gson gson) {
        this.productServiceInterf = productServiceInterf;
        this.gson = gson;
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto newProductDto = productServiceInterf.createProduct(productDto);
            if (newProductDto != null) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ApiResponse(HttpStatus.CREATED.value(),
                                "Product created successfully",
                                newProductDto));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST.value())
                        .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                                "Product creation failed",
                                "Invalid Category id"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Product creation failed", e);
        }
    }
}
