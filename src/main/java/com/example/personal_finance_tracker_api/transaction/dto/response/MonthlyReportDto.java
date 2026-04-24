package com.example.personal_finance_tracker_api.transaction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyReportDto {
    private String month;
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
}
