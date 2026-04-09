package com.example.personal_finance_tracker_api.transaction.service;

import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);

    public List<Transaction> getAllTransaction();

    public Transaction getTransactionById(Long id);

    public Transaction updateTransaction(Long id, Transaction updateTrans);

    public Void deleteTransaction(Long id);

    public Map<String, Object> getMonthlyReport(int month, int year);
}
