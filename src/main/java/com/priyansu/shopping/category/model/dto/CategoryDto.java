package com.priyansu.shopping.category.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoryDto {

    private Long id;

    private String categoryName;

    private String description;

    private String imageUrl;

}
