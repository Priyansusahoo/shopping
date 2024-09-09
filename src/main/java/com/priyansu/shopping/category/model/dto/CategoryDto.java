package com.priyansu.shopping.category.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoryDto {

    @JsonProperty("id")
    private Long id;

    @Size(min = 1, max = 100)
    @NotBlank(message = "categoryName must not be blank")
    @JsonProperty("categoryName")
    private String categoryName;

    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("description")
    private String description;

    @Size(min = 1, max = 2500, message = "Image URL must not exceed 2500 characters")
    @NotBlank(message = "Image URL must not be blank")
    @JsonProperty("imageUrl")
    private String imageUrl;

}
