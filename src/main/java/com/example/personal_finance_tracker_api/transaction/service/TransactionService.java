package com.example.personal_finance_tracker_api.transaction.service;

import com.example.personal_finance_tracker_api.transaction.dto.request.TransactionRequestDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.MonthlyReportDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.TransactionResponseDto;
import com.example.personal_finance_tracker_api.common.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto requestDto);

    Page<TransactionResponseDto> getAllTransaction(LocalDate startDate, LocalDate endDate, Long categoryId, Type type, Pageable pageable);

    String exportTransactionsToCsv(LocalDate startDate, LocalDate endDate, Long categoryId, Type type);

    TransactionResponseDto getTransactionById(Long id);

    TransactionResponseDto updateTransaction(Long id, TransactionRequestDto updateTrans);

    Void deleteTransaction(Long id);

    MonthlyReportDto getMonthlyReport(int month, int year);
}
