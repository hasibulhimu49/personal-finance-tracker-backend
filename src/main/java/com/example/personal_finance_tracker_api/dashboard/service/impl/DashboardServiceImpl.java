package com.example.personal_finance_tracker_api.dashboard.service.impl;

import com.example.personal_finance_tracker_api.dashboard.dto.CategoryBreakdownDto;
import com.example.personal_finance_tracker_api.dashboard.service.DashboardService;
import com.example.personal_finance_tracker_api.common.util.SecurityUtils;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import com.example.personal_finance_tracker_api.transaction.repository.TransactionRepository;
import com.example.personal_finance_tracker_api.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TransactionRepository transactionRepository;

    @Override
    public MonthlyReportDto getMonthlySummary(int month, int year) {
        Long userId = SecurityUtils.getCurrentUser().getId();
        Double income = transactionRepository.getTotalIncome(userId, month, year);
        Double expense = transactionRepository.getTotalExpense(userId, month, year);

        if (income == null) income = 0.0;
        if (expense == null) expense = 0.0;

        return new MonthlyReportDto(year + "-" + month, income, expense, income - expense);
    }

    @Override
    public List<CategoryBreakdownDto> getCategoryBreakdown(int month, int year) {
        Long userId = SecurityUtils.getCurrentUser().getId();
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);
        
        return transactions.stream()
                .filter(t -> t.getLocalDate().getMonthValue() == month && t.getLocalDate().getYear() == year)
                .filter(t -> t.getType() == com.example.personal_finance_tracker_api.common.enums.Type.EXPENSE)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.summingDouble(Transaction::getAmount)
                ))
                .entrySet().stream()
                .map(e -> new CategoryBreakdownDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
