package com.priyansu.shopping.category.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Represents a product category in the e-commerce application.
 * Includes category name, description, mandatory image, creator, and last modifier.
 * <p>
 * Created by: Priyansu
 * Last Modified by: Priyansu (2024-09-07)
 */
@Entity
@Table(name = "category")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false, length = 100)
    @Size(min = 1, max = 100, message = "Category name must not exceed 100 characters")
    @NotBlank(message = "Category name must not be blank")
    @JsonProperty("categoryName")
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Description must not be blank")
    @Size(min = 1, max = 255, message = "Description must not exceed 255 characters")
    @JsonProperty("description")
    private String description;

    /*@Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonProperty("image")
    private byte[] image;*/

    @Column(name = "image_url", length = 2500, nullable = false)
    @Size(min = 1, max = 2500, message = "Image URL must not exceed 2500 characters")
    @NotBlank(message = "Image URL must not be blank")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

}
