package com.priyansu.shopping.product.service;

import com.priyansu.shopping.category.model.entity.Category;
import com.priyansu.shopping.category.model.jparepository.CategoryRepository;
import com.priyansu.shopping.product.model.dto.ProductDto;
import com.priyansu.shopping.product.model.entity.Product;
import com.priyansu.shopping.product.model.jparepository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("productService")
@Slf4j
public class ProductService implements ProductServiceInterf{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        try {
            productDto.setId(null);
            Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
            if (category.isPresent()) {
                Product product = modelMapper.map(productDto, Product.class);
                product.setCategory(category.get());
                return modelMapper.map(productRepository.save(product), ProductDto.class);
            } else {
                log.error("Category with id {} not found", productDto.getCategoryId());
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
