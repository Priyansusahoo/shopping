package com.priyansu.shopping.product.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.priyansu.shopping.category.model.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a product in the e-commerce application.
 * Includes product id, name, description, price, imageURL.
 * <p>
 * Created by: Priyansu
 * Last Modified by: Priyansu (2024-09-12)
 */

@Entity
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, length = 255)
    @Size(min = 1, max = 255, message = "Product name must not exceed 255 characters")
    @NotBlank(message = "Product name must not be blank")
    private String name;

    @JsonProperty("imageURL")
    @Column(name = "image_url", length = 2500, nullable = false)
    @Size(min = 1, max = 2500, message = "Image URL must not exceed 2500 characters")
    @NotBlank(message = "Image URL must not be blank")
    private String imageURL;

    @JsonProperty("price")
    @Column(name = "price", nullable = false)
    @NotNull(message = "Price must not be blank")
    private double price;

    @JsonProperty("description")
    @Size(min = 1, max = 6000, message = "Description must not exceed 6000 characters")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonProperty("category")
    Category category;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
