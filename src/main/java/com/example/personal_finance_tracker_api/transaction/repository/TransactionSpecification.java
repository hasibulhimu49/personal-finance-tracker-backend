package com.example.personal_finance_tracker_api.transaction.repository;

import com.example.personal_finance_tracker_api.common.enums.Type;
import com.example.personal_finance_tracker_api.transaction.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionSpecification {

    public static Specification<Transaction> getTransactionsByFilters(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Long categoryId,
            Type type
    ) {
        Specification<Transaction> spec = hasUserId(userId);

        if (startDate != null && endDate != null) {
            spec = spec.and(hasDateBetween(startDate, endDate));
        } else if (startDate != null) {
            spec = spec.and(hasDateAfterOrEqual(startDate));
        } else if (endDate != null) {
            spec = spec.and(hasDateBeforeOrEqual(endDate));
        }

        if (categoryId != null) {
            spec = spec.and(hasCategoryId(categoryId));
        }

        if (type != null) {
            spec = spec.and(hasType(type));
        }

        return spec;
    }





    private static Specification<Transaction> hasUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Transaction> hasDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> cb.between(root.get("localDate"), startDate, endDate);
    }

    private static Specification<Transaction> hasDateAfterOrEqual(LocalDate startDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("localDate"), startDate);
    }

    private static Specification<Transaction> hasDateBeforeOrEqual(LocalDate endDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("localDate"), endDate);
    }

    private static Specification<Transaction> hasCategoryId(Long categoryId) {
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }

    private static Specification<Transaction> hasType(Type type) {
        return (root, query, cb) -> cb.equal(root.get("type"), type);
    }
}
