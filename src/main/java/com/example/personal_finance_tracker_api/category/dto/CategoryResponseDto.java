package com.example.personal_finance_tracker_api.category.dto;

import com.example.personal_finance_tracker_api.common.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String icon;
    private String color;
    private Type type;
    private boolean isDefault;
}
