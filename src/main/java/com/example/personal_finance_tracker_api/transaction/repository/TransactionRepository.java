package com.example.personal_finance_tracker_api.transaction.repository;

import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findAllByUserId(Long userId);

    Optional<Transaction> findByIdAndUserId(Long id, Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'INCOME' AND MONTH(t.localDate) = :month AND YEAR(t.localDate) = :year")
    Double getTotalIncome(Long userId, int month, int year);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'EXPENSE' AND MONTH(t.localDate) = :month AND YEAR(t.localDate) = :year")
    Double getTotalExpense(Long userId, int month, int year);
}
