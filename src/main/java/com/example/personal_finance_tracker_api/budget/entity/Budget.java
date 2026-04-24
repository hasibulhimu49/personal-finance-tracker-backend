package com.example.personal_finance_tracker_api.budget.entity;

import com.example.personal_finance_tracker_api.category.entity.Category;
import com.example.personal_finance_tracker_api.common.audit.BaseEntity;
import com.example.personal_finance_tracker_api.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Budget_Table")
@Getter
@Setter
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer month;

    @Column(name = "\"year\"", nullable = false)
    private Integer year;
}
