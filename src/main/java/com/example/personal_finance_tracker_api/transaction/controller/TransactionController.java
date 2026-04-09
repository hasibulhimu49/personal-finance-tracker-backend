package com.example.personal_finance_tracker_api.transaction.controller;

import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import com.example.personal_finance_tracker_api.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Tag(name = "Transaction API",description = "APIs for managing financial transactions")
public class TransactionController {

    private final TransactionService service;

    //Create Transaction
    @Operation(summary = "Create a new transaction", description = "Add a new income or expense transaction")
    @PostMapping()
    public Transaction createTransaction(
            @RequestBody Transaction transaction)
    {
        return service.createTransaction(transaction);
    }


    @Operation(summary = "Get all transactions", description = "Retrieve all transactions from the system")
    @GetMapping()
    public List<Transaction> getAllTransaction()
    {
        return service.getAllTransaction();
    }


    @Operation(summary = "Get transaction by ID", description = "Retrieve a specific transaction using its ID")
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id)
    {
        return service.getTransactionById(id);
    }


    @Operation(summary = "Update transaction", description = "Update an existing transaction by ID")
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updateTrans)
    {
        return service.updateTransaction(id,updateTrans);
    }


    @Operation(summary = "Delete transaction", description = "Delete an existing transaction by ID")
    @DeleteMapping("{id}")
    public Void deletetransaction(@PathVariable Long id)
    {
        return service.deleteTransaction(id);
    }



    @Operation(summary = "Monthly Report", description = "Monthly report for total income and expense")
    @GetMapping("/reports/monthly")
    public Map<String, Object> getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year) {

        return service.getMonthlyReport(month, year);
    }

}
