package com.example.personal_finance_tracker_api.budget.dto;

import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BudgetActualDto {
    private CategoryResponseDto category;
    private Double budgetedAmount;
    private Double actualSpent;
    private Double remainingAmount;
    private Double percentageUsed;
}
