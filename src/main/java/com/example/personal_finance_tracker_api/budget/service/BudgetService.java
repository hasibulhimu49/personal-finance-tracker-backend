package com.example.personal_finance_tracker_api.budget.service;

import com.example.personal_finance_tracker_api.budget.dto.BudgetActualDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetRequestDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetResponseDto;

import java.util.List;

public interface BudgetService {
    BudgetResponseDto createOrUpdateBudget(BudgetRequestDto requestDto);
    List<BudgetResponseDto> getBudgetsByMonth(int month, int year);
    BudgetResponseDto getBudgetById(Long id);
    Void deleteBudget(Long id);
    List<BudgetActualDto> getBudgetVsActual(int month, int year);
}
