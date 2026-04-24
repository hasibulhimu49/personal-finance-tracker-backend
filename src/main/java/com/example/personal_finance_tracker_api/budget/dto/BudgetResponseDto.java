package com.example.personal_finance_tracker_api.budget.dto;

import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetResponseDto {
    private Long id;
    private CategoryResponseDto category;
    private Double amount;
    private Integer month;
    private Integer year;
}
