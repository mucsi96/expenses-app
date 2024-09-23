package io.github.mucsi96.expense_tracker.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant date;
    private String description;
    private String location;
    private String category;
    private BigDecimal amount;
    private String currency;
    private String method;
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Expense expense = (Expense) o;

        boolean dateEquals = this.date == null && expense.date == null || (this.date != null && expense.date != null &&
                LocalDate.ofInstant(this.date, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE)
                        .equals(LocalDate.ofInstant(expense.date, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE)));

        boolean descriptionEquals = Objects.equals(this.description, expense.description);

        boolean amountEquals = this.amount == null && expense.amount == null || (this.amount != null
                && expense.amount != null &&
                this.amount.setScale(0, RoundingMode.DOWN).equals(expense.amount.setScale(0, RoundingMode.DOWN)));
        return dateEquals && descriptionEquals && amountEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.date != null ? LocalDate.ofInstant(this.date, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE)
                        : null,
                this.description,
                this.amount != null ? this.amount.setScale(0, RoundingMode.DOWN) : null);
    }
}
