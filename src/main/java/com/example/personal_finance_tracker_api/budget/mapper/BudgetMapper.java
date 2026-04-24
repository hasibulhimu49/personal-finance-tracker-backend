package com.example.personal_finance_tracker_api.budget.mapper;

import com.example.personal_finance_tracker_api.budget.dto.BudgetRequestDto;
import com.example.personal_finance_tracker_api.budget.dto.BudgetResponseDto;
import com.example.personal_finance_tracker_api.budget.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(target = "category", ignore = true)
    Budget toEntity(BudgetRequestDto requestDto);

    BudgetResponseDto toDto(Budget entity);

    List<BudgetResponseDto> toDtoList(List<Budget> entities);
}
