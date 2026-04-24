package com.example.personal_finance_tracker_api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryBreakdownDto {
    private String categoryName;
    private Double amountSpent;
}
