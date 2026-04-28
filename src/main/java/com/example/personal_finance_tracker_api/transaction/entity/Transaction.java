package com.example.personal_finance_tracker_api.transaction.entity;

import com.example.personal_finance_tracker_api.category.entity.Category;
import com.example.personal_finance_tracker_api.common.enums.Type;
import com.example.personal_finance_tracker_api.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.example.personal_finance_tracker_api.common.audit.BaseEntity;

@Entity
@Table(name = "Transaction_Table")
@Getter
@Setter
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(type = "string", format = "date", example = "31/03/2026")
    @Column(name = "transaction_date")
    private LocalDate localDate;
}
