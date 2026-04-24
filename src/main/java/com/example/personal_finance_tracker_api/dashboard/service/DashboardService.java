package com.example.personal_finance_tracker_api.dashboard.service;

import com.example.personal_finance_tracker_api.dashboard.dto.CategoryBreakdownDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;

import java.util.List;

public interface DashboardService {
    MonthlyReportDto getMonthlySummary(int month, int year);
    List<CategoryBreakdownDto> getCategoryBreakdown(int month, int year);
}
