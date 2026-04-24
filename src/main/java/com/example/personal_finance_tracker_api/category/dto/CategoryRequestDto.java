package com.example.personal_finance_tracker_api.category.dto;

import com.example.personal_finance_tracker_api.common.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    private String name;

    private String icon;

    private String color;

    @NotNull(message = "Category type is required")
    private Type type;
}
