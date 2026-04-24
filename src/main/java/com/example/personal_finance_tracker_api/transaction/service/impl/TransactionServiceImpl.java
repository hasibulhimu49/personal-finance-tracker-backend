package com.example.personal_finance_tracker_api.transaction.service.impl;

import com.example.personal_finance_tracker_api.category.entity.Category;
import com.example.personal_finance_tracker_api.category.repository.CategoryRepository;
import com.example.personal_finance_tracker_api.common.enums.Type;
import com.example.personal_finance_tracker_api.common.exception.ResourceNotFoundException;
import com.example.personal_finance_tracker_api.common.util.SecurityUtils;
import com.example.personal_finance_tracker_api.transaction.dto.request.TransactionRequestDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.TransactionResponseDto;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import com.example.personal_finance_tracker_api.transaction.mapper.TransactionMapper;
import com.example.personal_finance_tracker_api.transaction.repository.TransactionRepository;
import com.example.personal_finance_tracker_api.transaction.repository.TransactionSpecification;
import com.example.personal_finance_tracker_api.transaction.service.TransactionService;
import com.example.personal_finance_tracker_api.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final CategoryRepository categoryRepository;

    private Category getCategory(Long categoryId, Long userId) {
        return categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    //Create Transaction
    public TransactionResponseDto createTransaction(TransactionRequestDto requestDto) {
        User currentUser = SecurityUtils.getCurrentUser();
        Category category = getCategory(requestDto.getCategoryId(), currentUser.getId());
        
        Transaction transaction = mapper.toEntity(requestDto);
        transaction.setCategory(category);
        transaction.setLocalDate(LocalDate.now());
        transaction.setUser(currentUser);
        
        Transaction saved = repository.save(transaction);
        return mapper.toDto(saved);
    }

    //Get All Transaction
    public Page<TransactionResponseDto> getAllTransaction(LocalDate startDate, LocalDate endDate, Long categoryId, Type type, Pageable pageable) {
        User currentUser = getCurrentUser();
        Specification<Transaction> spec = TransactionSpecification.getTransactionsByFilters(currentUser.getId(), startDate, endDate, categoryId, type);
        Page<Transaction> transactions = repository.findAll(spec, pageable);
        return transactions.map(mapper::toDto);
    }

    public String exportTransactionsToCsv(LocalDate startDate, LocalDate endDate, Long categoryId, Type type) {
        User currentUser = getCurrentUser();
        Specification<Transaction> spec = TransactionSpecification.getTransactionsByFilters(currentUser.getId(), startDate, endDate, categoryId, type);
        List<Transaction> transactions = repository.findAll(spec);

        StringBuilder csv = new StringBuilder();
        csv.append("ID,Date,Category,Type,Amount\n");
        for (Transaction t : transactions) {
            String catName = (t.getCategory() != null) ? t.getCategory().getName() : "Uncategorized";
            csv.append(t.getId()).append(",")
               .append(t.getLocalDate()).append(",")
               .append("\"").append(catName).append("\",")
               .append(t.getType()).append(",")
               .append(t.getAmount()).append("\n");
        }
        return csv.toString();
    }

    //Get Transaction By ID
    public TransactionResponseDto getTransactionById(Long id) {
        User currentUser = getCurrentUser();
        Transaction t = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() ->new ResourceNotFoundException("Transaction not found with id: " + id));
        return mapper.toDto(t);
    }

    public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto updateTrans) {
        User currentUser = getCurrentUser();
        Transaction t = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        if (updateTrans.getAmount() != null) {
            t.setAmount(updateTrans.getAmount());
        }

        if (updateTrans.getType() != null) {
            t.setType(updateTrans.getType());
        }

        if (updateTrans.getCategoryId() != null) {
            Category category = getCategory(updateTrans.getCategoryId(), currentUser.getId());
            t.setCategory(category);
        }

        Transaction updated = repository.save(t);
        return mapper.toDto(updated);
    }

    //Delete Transaction
    public Void deleteTransaction(Long id) {
        User currentUser = getCurrentUser();
        Transaction t = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        repository.delete(t);
        return null;
    }



    public MonthlyReportDto getMonthlyReport(int month, int year) {
        User currentUser = getCurrentUser();
        Long userId = currentUser.getId();

        Double income = repository.getTotalIncome(userId, month, year);
        Double expense = repository.getTotalExpense(userId, month, year);

        if (income == null) income = 0.0;
        if (expense == null) expense = 0.0;

        Double balance = income - expense;

        return new MonthlyReportDto(year + "-" + month, income, expense, balance);
    }
}
