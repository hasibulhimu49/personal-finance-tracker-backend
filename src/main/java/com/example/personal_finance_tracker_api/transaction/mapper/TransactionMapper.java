package com.example.personal_finance_tracker_api.transaction.mapper;

import com.example.personal_finance_tracker_api.transaction.dto.request.TransactionRequestDto;
import com.example.personal_finance_tracker_api.transaction.dto.response.TransactionResponseDto;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "category", ignore = true)
    Transaction toEntity(TransactionRequestDto requestDto);

    TransactionResponseDto toDto(Transaction entity);

    List<TransactionResponseDto> toDtoList(List<Transaction> entities);
}
