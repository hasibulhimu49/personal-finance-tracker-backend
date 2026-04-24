package com.example.personal_finance_tracker_api.transaction.dto.request;

import com.example.personal_finance_tracker_api.common.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {

    @NotNull(message = "Amount is required")
    @PositiveOrZero(message = "Amount must be a positive number or zero")
    private Double amount;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Type is required")
    private Type type;
}
