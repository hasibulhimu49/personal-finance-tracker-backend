package com.example.personal_finance_tracker_api.category.service.impl;

import com.example.personal_finance_tracker_api.category.dto.CategoryRequestDto;
import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;
import com.example.personal_finance_tracker_api.category.entity.Category;
import com.example.personal_finance_tracker_api.category.mapper.CategoryMapper;
import com.example.personal_finance_tracker_api.category.repository.CategoryRepository;
import com.example.personal_finance_tracker_api.category.service.CategoryService;
import com.example.personal_finance_tracker_api.common.enums.Type;
import com.example.personal_finance_tracker_api.common.exception.ResourceNotFoundException;
import com.example.personal_finance_tracker_api.common.util.SecurityUtils;
import com.example.personal_finance_tracker_api.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category = mapper.toEntity(requestDto);
        category.setUser(SecurityUtils.getCurrentUser());
        category.setDefault(false);
        return mapper.toDto(repository.save(category));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        User currentUser = SecurityUtils.getCurrentUser();
        return mapper.toDtoList(repository.findAllByUserId(currentUser.getId()));
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        User currentUser = SecurityUtils.getCurrentUser();
        Category category = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapper.toDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto) {
        User currentUser = SecurityUtils.getCurrentUser();
        Category category = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        category.setName(requestDto.getName());
        category.setIcon(requestDto.getIcon());
        category.setColor(requestDto.getColor());
        category.setType(requestDto.getType());
        
        return mapper.toDto(repository.save(category));
    }

    @Override
    public Void deleteCategory(Long id) {
        User currentUser = SecurityUtils.getCurrentUser();
        Category category = repository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        repository.delete(category);
        return null;
    }

    @Override
    public void seedDefaultCategories(User user) {
        List<Category> defaults = Arrays.asList(
                createDefault(user, "Food & Dining", Type.EXPENSE, "fastfood", "#FF5733"),
                createDefault(user, "Transportation", Type.EXPENSE, "directions_car", "#3380FF"),
                createDefault(user, "Utilities", Type.EXPENSE, "bolt", "#FFC300"),
                createDefault(user, "Salary", Type.INCOME, "attach_money", "#28B463"),
                createDefault(user, "Investments", Type.INCOME, "trending_up", "#8E44AD")
        );
        repository.saveAll(defaults);
    }

    private Category createDefault(User user, String name, Type type, String icon, String color) {
        Category c = new Category();
        c.setUser(user);
        c.setName(name);
        c.setType(type);
        c.setIcon(icon);
        c.setColor(color);
        c.setDefault(true);
        return c;
    }
}
