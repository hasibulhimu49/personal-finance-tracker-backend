package com.example.personal_finance_tracker_api.transaction.controller;

import com.example.personal_finance_tracker_api.common.response.ApiResponse;
import com.example.personal_finance_tracker_api.transaction.dto.request.TransactionRequestDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.TransactionResponseDto;
import com.example.personal_finance_tracker_api.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.personal_finance_tracker_api.common.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Tag(name = "Transaction API",description = "APIs for managing financial transactions")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService service;


    //Create Transaction
    @Operation(summary = "Create a new transaction", description = "Add a new income or expense transaction")
    @PostMapping()
    public ResponseEntity<ApiResponse<TransactionResponseDto>> createTransaction(
            @Valid @RequestBody TransactionRequestDto transaction)
    {
        TransactionResponseDto response = service.createTransaction(transaction);
        return ResponseEntity.ok(ApiResponse.success("Transaction created successfully", response));
    }



    @Operation(summary = "Get all transactions", description = "Retrieve all transactions with optional filters and pagination")
    @GetMapping()
    public ResponseEntity<ApiResponse<Page<TransactionResponseDto>>> getAllTransaction(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Type type,
            Pageable pageable
    )
    {
        Page<TransactionResponseDto> responses = service.getAllTransaction(startDate, endDate, categoryId, type, pageable);
        return ResponseEntity.ok(ApiResponse.success("Transactions retrieved successfully", responses));
    }





    @Operation(summary = "Get transaction by ID", description = "Retrieve a specific transaction using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponseDto>> getTransactionById(@PathVariable Long id)
    {
        TransactionResponseDto response = service.getTransactionById(id);
        return ResponseEntity.ok(ApiResponse.success("Transaction found", response));
    }





    @Operation(summary = "Update transaction", description = "Update an existing transaction by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponseDto>> updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequestDto updateTrans)
    {
        TransactionResponseDto response = service.updateTransaction(id,updateTrans);
        return ResponseEntity.ok(ApiResponse.success("Transaction updated successfully", response));
    }





    @Operation(summary = "Delete transaction", description = "Delete an existing transaction by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deletetransaction(@PathVariable Long id)
    {
        service.deleteTransaction(id);
        return ResponseEntity.ok(ApiResponse.success("Transaction deleted successfully", null));
    }






    @Operation(summary = "Monthly Report", description = "Monthly report for total income and expense")
    @GetMapping("/reports/monthly")
    public ResponseEntity<ApiResponse<MonthlyReportDto>> getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year) {

        MonthlyReportDto report = service.getMonthlyReport(month, year);
        return ResponseEntity.ok(ApiResponse.success("Monthly report generated", report));
    }






    @Operation(summary = "Export transactions to CSV", description = "Download a CSV file of transactions with matching filters")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportTransactionsToCsv(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Type type
    )
    {
        String csvData = service.exportTransactionsToCsv(startDate, endDate, categoryId, type);
        byte[] output = csvData.getBytes();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"transactions.csv\"")
                .header("Content-Type", "text/csv")
                .body(output);
    }



}
