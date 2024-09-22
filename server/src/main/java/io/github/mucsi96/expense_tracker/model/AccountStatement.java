package io.github.mucsi96.expense_tracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountStatement {
    private final Optional<LocalDate> tradeDate;
    private final Optional<LocalTime> tradeTime;
    private final Optional<LocalDate> bookingDate;
    private final Optional<LocalDate> valueDate;
    private final String currency;
    private final Optional<BigDecimal> debit;
    private final Optional<BigDecimal> credit;
    private final Optional<BigDecimal> individualAmount;
    private final Optional<BigDecimal> balance;
    private final String transactionNo;
    private final String description1;
    private final String description2;
    private final String description3;
    private final String footnotes;
}
