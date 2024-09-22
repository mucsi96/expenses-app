package io.github.mucsi96.expense_tracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardStatement {
    private final String accountNumber;
    private final String cardNumber;
    private final String accountCardholder;
    private final Optional<LocalDate> purchaseDate;
    private final String bookingText;
    private final String sector;
    private final Optional<BigDecimal> amount;
    private final String originalCurrency;
    private final Optional<BigDecimal> rate;
    private final String currency;
    private final Optional<BigDecimal> debit;
    private final Optional<BigDecimal> credit;
    private final boolean booked;
}
