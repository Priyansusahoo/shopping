package com.priyansu.shopping.category.model.entity;

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
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    /*@Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private byte[] image;*/

    @Column(name = "image_url", length = 1500)
    @NotBlank
    private String imageUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
