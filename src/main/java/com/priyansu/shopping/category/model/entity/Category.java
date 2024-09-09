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

    @Column(name = "category_name")
    @Size(min = 1, max = 100)
    @NotBlank
    @JsonProperty("categoryName")
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("description")
    private String description;

    /*@Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonProperty("image")
    private byte[] image;*/

    @Column(name = "image_url", length = 2500)
    @Size(min = 1, max = 2500)
    @NotBlank(message = "Image URL must not be blank")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

}
