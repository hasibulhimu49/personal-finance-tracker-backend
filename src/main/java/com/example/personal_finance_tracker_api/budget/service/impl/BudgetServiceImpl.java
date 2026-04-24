package com.example.personal_finance_tracker_api.budget.service.impl;

import com.example.personal_finance_tracker_api.budget.dto.BudgetActualDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetRequestDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetResponseDto;
import com.example.personal_finance_tracker_api.budget.entity.Budget;
import com.example.personal_finance_tracker_api.budget.mapper.BudgetMapper;
import com.example.personal_finance_tracker_api.budget.repository.BudgetRepository;
import com.example.personal_finance_tracker_api.budget.service.BudgetService;
import com.example.personal_finance_tracker_api.category.entity.Category;
import com.example.personal_finance_tracker_api.category.mapper.CategoryMapper;
import com.example.personal_finance_tracker_api.category.repository.CategoryRepository;
import com.example.personal_finance_tracker_api.common.exception.ResourceNotFoundException;
import com.example.personal_finance_tracker_api.security.UserPrincipal;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import com.example.personal_finance_tracker_api.transaction.repository.TransactionRepository;
import com.example.personal_finance_tracker_api.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TransactionRepository transactionRepository;

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal userPrincipal) {
            return userPrincipal.getUser();
        }
        throw new RuntimeException("User not authenticated");
    }

    @Override
    public BudgetResponseDto createOrUpdateBudget(BudgetRequestDto requestDto) {
        User user = getCurrentUser();
        Category category = categoryRepository.findByIdAndUserId(requestDto.getCategoryId(), user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Optional<Budget> existingOpt = budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(
                user.getId(), category.getId(), requestDto.getMonth(), requestDto.getYear());

        Budget budget;
        if (existingOpt.isPresent()) {
            budget = existingOpt.get();
            budget.setAmount(requestDto.getAmount());
        } else {
            budget = budgetMapper.toEntity(requestDto);
            budget.setCategory(category);
            budget.setUser(user);
        }

        return budgetMapper.toDto(budgetRepository.save(budget));
    }

    @Override
    public List<BudgetResponseDto> getBudgetsByMonth(int month, int year) {
        User user = getCurrentUser();
        List<Budget> budgets = budgetRepository.findAllByUserIdAndMonthAndYear(user.getId(), month, year);
        return budgetMapper.toDtoList(budgets);
    }

    @Override
    public BudgetResponseDto getBudgetById(Long id) {
        User user = getCurrentUser();
        Budget budget = budgetRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        return budgetMapper.toDto(budget);
    }

    @Override
    public Void deleteBudget(Long id) {
        User user = getCurrentUser();
        Budget budget = budgetRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        budgetRepository.delete(budget);
        return null;
    }

    @Override
    public List<BudgetActualDto> getBudgetVsActual(int month, int year) {
        User user = getCurrentUser();
        List<Budget> budgets = budgetRepository.findAllByUserIdAndMonthAndYear(user.getId(), month, year);
        List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());

        // Sum actuals grouped by category ID where type is EXPENSE and month matches
        Map<Long, Double> actualsPerCategory = transactions.stream()
                .filter(t -> t.getLocalDate().getMonthValue() == month && t.getLocalDate().getYear() == year)
                .filter(t -> t.getType() == com.example.personal_finance_tracker_api.common.enums.Type.EXPENSE)
                .filter(t -> t.getCategory() != null)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getId(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        List<BudgetActualDto> results = new ArrayList<>();
        for (Budget budget : budgets) {
            Double budgeted = budget.getAmount();
            Double actual = actualsPerCategory.getOrDefault(budget.getCategory().getId(), 0.0);
            Double remaining = budgeted - actual;
            Double percentage = (budgeted > 0) ? (actual / budgeted) * 100 : 0.0;

            results.add(new BudgetActualDto(
                    categoryMapper.toDto(budget.getCategory()),
                    budgeted,
                    actual,
                    remaining,
                    percentage
            ));
        }

        return results;
    }
}
