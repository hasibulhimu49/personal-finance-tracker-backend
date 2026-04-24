package com.example.personal_finance_tracker_api.category.service;

import com.example.personal_finance_tracker_api.category.dto.CategoryRequestDto;
import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto requestDto);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto);
    Void deleteCategory(Long id);
    void seedDefaultCategories(com.example.personal_finance_tracker_api.user.entity.User user);
}
