package com.example.personal_finance_tracker_api.transaction.repository;

import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {



    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME' AND MONTH(t.localDate) = :month AND YEAR(t.localDate) = :year")
    Double getTotalIncome(int month, int year);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE' AND MONTH(t.localDate) = :month AND YEAR(t.localDate) = :year")
    Double getTotalExpense(int month, int year);
}
