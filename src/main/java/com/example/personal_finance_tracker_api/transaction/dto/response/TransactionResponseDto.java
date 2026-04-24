package com.example.personal_finance_tracker_api.transaction.dto.response;

import com.example.personal_finance_tracker_api.common.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.example.personal_finance_tracker_api.category.dto.CategoryResponseDto;

@Getter
@Setter
public class TransactionResponseDto {

    private Long id;
    private Double amount;
    private CategoryResponseDto category;
    private Type type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(type = "string", format = "date", example = "31/03/2026")
    private LocalDate localDate;

    @Schema(description = "Date when transaction was recorded")
    private LocalDate createdAt;
}
