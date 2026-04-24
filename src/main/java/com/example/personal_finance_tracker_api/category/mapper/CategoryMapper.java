package com.example.personal_finance_tracker_api.category.mapper;

import com.example.personal_finance_tracker_api.category.dto.CategoryRequestDto;
import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;
import com.example.personal_finance_tracker_api.category.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequestDto requestDto);

    CategoryResponseDto toDto(Category entity);

    List<CategoryResponseDto> toDtoList(List<Category> entities);
}
