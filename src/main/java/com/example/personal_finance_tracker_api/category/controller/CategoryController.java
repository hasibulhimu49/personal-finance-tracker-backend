package com.example.personal_finance_tracker_api.category.controller;

import com.example.personal_finance_tracker_api.category.dto.CategoryRequestDto;
import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;
import com.example.personal_finance_tracker_api.category.service.CategoryService;
import com.example.personal_finance_tracker_api.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
@Tag(name = "Category API", description = "APIs for managing transaction categories")
public class CategoryController {

    private final CategoryService service;

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(@Valid @RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto response = service.createCategory(requestDto);
        return ResponseEntity.ok(ApiResponse.success("Category created", response));
    }

    @Operation(summary = "Get all categories for current user")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success("Categories retrieved", service.getAllCategories()));
    }

    @Operation(summary = "Get category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Category retrieved", service.getCategoryById(id)));
    }

    @Operation(summary = "Update category")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success("Category updated", service.updateCategory(id, requestDto)));
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted", null));
    }
}
