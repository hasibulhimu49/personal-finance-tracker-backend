package com.example.personal_finance_tracker_api.dashboard.controller;

import com.example.personal_finance_tracker_api.common.response.ApiResponse;
import com.example.personal_finance_tracker_api.dashboard.dto.CategoryBreakdownDto;
import com.example.personal_finance_tracker_api.dashboard.service.DashboardService;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
@AllArgsConstructor
@Tag(name = "Dashboard API", description = "APIs for dashboard analytics and reporting")
@SecurityRequirement(name = "bearerAuth")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Monthly Report Summary", description = "Get total income, expense, and balance for a specific month")
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<MonthlyReportDto>> getMonthlySummary(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(ApiResponse.success("Monthly summary generated", dashboardService.getMonthlySummary(month, year)));
    }

    @Operation(summary = "Category Breakdown", description = "Get expenses broken down by category for a specific month")
    @GetMapping("/category-breakdown")
    public ResponseEntity<ApiResponse<List<CategoryBreakdownDto>>> getCategoryBreakdown(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(ApiResponse.success("Category breakdown generated", dashboardService.getCategoryBreakdown(month, year)));
    }
}
