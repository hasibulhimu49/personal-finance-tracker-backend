package com.example.personal_finance_tracker_api.transaction.service.impl;

import com.example.personal_finance_tracker_api.common.exception.ResourceNotFoundException;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import com.example.personal_finance_tracker_api.transaction.repository.TransactionRepository;
import com.example.personal_finance_tracker_api.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    //Create Transaction
    public Transaction createTransaction(Transaction transaction) {
        transaction.setLocalDate(LocalDate.now());
        return repository.save(transaction);
    }

    //Get All Transaction
    public List<Transaction> getAllTransaction() {
        return repository.findAll();
    }

    //Get Transaction By ID
    public Transaction getTransactionById(Long id) {
        return repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    public Transaction updateTransaction(Long id, Transaction updateTrans) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        if (updateTrans.getAmount() != null) {
            t.setAmount(updateTrans.getAmount());
        }

        if (updateTrans.getType() != null) {
            t.setType(updateTrans.getType());
        }

        if (updateTrans.getLocalDate() != null) {
            t.setLocalDate(updateTrans.getLocalDate());
        }

        if (updateTrans.getCategory() != null) {
            t.setCategory(updateTrans.getCategory());
        }

        return repository.save(t);
    }

    //Delete Transaction
    public Void deleteTransaction(Long id) {
        repository.deleteById(id);
        return null;
    }



    public Map<String, Object> getMonthlyReport(int month, int year) {

        Double income = repository.getTotalIncome(month, year);
        Double expense = repository.getTotalExpense(month, year);

        if (income == null) income = 0.0;
        if (expense == null) expense = 0.0;

        Double balance = income - expense;

        Map<String, Object> report = new HashMap<>();
        report.put("month", year + "-" + month);
        report.put("totalIncome", income);
        report.put("totalExpense", expense);
        report.put("balance", balance);

/*
    Instead of Map → create class:
        public class MonthlyReportDTO {
            private String month;
            private Double totalIncome;
            private Double totalExpense;
            private Double balance;
        }*/

        return report;
    }
}
