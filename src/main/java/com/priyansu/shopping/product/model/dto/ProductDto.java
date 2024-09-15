package com.priyansu.shopping.product.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO class for Product.class
 * Includes fields from Product.class : name, imageURL, price, description, categoryId.
 * <p>
 * Created by: Priyansu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Size(min = 1, max = 255, message = "'name' must not exceed 255 characters")
    @NotBlank(message = "Product name must not be blank")
    private String name;

    @JsonProperty("imageURL")
    @Size(min = 1, max = 2500, message = "'imageURL' must not exceed 2500 characters")
    @NotBlank(message = "'imageURL' must not be blank")
    private String imageURL;

    @JsonProperty("price")
    @NotNull(message = "'price' must not be NULL")
    private double price;

    @JsonProperty("description")
    @Size(min = 1, max = 6000, message = "'description' must not exceed 6000 characters")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "'description' must not be blank")
    private String description;


    @NotNull(message = "'category_id' must not be NULL")
    @JsonProperty("categoryId")
    private Long categoryId;

}
