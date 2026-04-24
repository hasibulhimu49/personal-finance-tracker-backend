package com.example.personal_finance_tracker_api.budget.controller;

import com.example.personal_finance_tracker_api.budget.dto.BudgetActualDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetRequestDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetResponseDto;
import com.example.personal_finance_tracker_api.budget.service.BudgetService;
import com.example.personal_finance_tracker_api.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/budgets")
@AllArgsConstructor
@Tag(name = "Budget API", description = "APIs for creating and managing category budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Operation(summary = "Set or update a budget limit")
    @PostMapping
    public ResponseEntity<ApiResponse<BudgetResponseDto>> createOrUpdateBudget(@Valid @RequestBody BudgetRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success("Budget saved", budgetService.createOrUpdateBudget(requestDto)));
    }

    @Operation(summary = "Get budgets for a month")
    @GetMapping
    public ResponseEntity<ApiResponse<List<BudgetResponseDto>>> getBudgetsByMonth(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(ApiResponse.success("Budgets retrieved", budgetService.getBudgetsByMonth(month, year)));
    }

    @Operation(summary = "Get budget by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BudgetResponseDto>> getBudgetById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Budget retrieved", budgetService.getBudgetById(id)));
    }

    @Operation(summary = "Delete budget")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok(ApiResponse.success("Budget deleted", null));
    }

    @Operation(summary = "Get budget limit versus actual spent")
    @GetMapping("/vs-actual")
    public ResponseEntity<ApiResponse<List<BudgetActualDto>>> getBudgetVsActual(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(ApiResponse.success("Analytics retrieved", budgetService.getBudgetVsActual(month, year)));
    }
}
