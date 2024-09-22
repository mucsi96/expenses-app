package io.github.mucsi96.expense_tracker.converter;

import java.util.Optional;

import org.apache.commons.csv.CSVRecord;

import io.github.mucsi96.expense_tracker.enums.CSVType;
import io.github.mucsi96.expense_tracker.model.CardStatement;
import io.github.mucsi96.expense_tracker.util.ConvertUtils;

public class CardStatementConverter {
    public static Optional<CardStatement> fromCSVRecord(CSVRecord record) {
        if (record.size() != 13 || String.join(";", record.values()).equals(CSVType.CARD_STATEMENT.getHeader())) {
            return Optional.empty();
        }

        return Optional.of(CardStatement.builder()
                .accountNumber(record.get(0))
                .cardNumber(record.get(1))
                .accountCardholder(record.get(2))
                .purchaseDate(ConvertUtils.parseDate(record.get(3)))
                .bookingText(record.get(4))
                .sector(record.get(5))
                .amount(ConvertUtils.toBigDecimal(record.get(6)))
                .originalCurrency(record.get(7))
                .rate(ConvertUtils.toBigDecimal(record.get(8)))
                .currency(record.get(9))
                .debit(ConvertUtils.toBigDecimal(record.get(10)))
                .credit(ConvertUtils.toBigDecimal(record.get(11)))
                .booked(Boolean.parseBoolean(record.get(12)))
                .build());
    }
}
