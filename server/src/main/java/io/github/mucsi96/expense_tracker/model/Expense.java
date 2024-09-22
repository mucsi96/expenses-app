package io.github.mucsi96.expense_tracker.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Instant date;
    private final String description;
    private final String location;
    private final String category;
    private final BigDecimal amount;
    private final String currency;
    private final String method;
    private final String comment;
}
