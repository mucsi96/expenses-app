package io.github.mucsi96.expense_tracker.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ConvertUtils {
    public static Optional<LocalDate> parseDate(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        if (dateStr == null || dateStr.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(LocalDate.parse(dateStr, dateFormatter));
    }

    public static Optional<LocalTime> parseTime(String timeStr) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        if (timeStr == null || timeStr.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(LocalTime.parse(timeStr, timeFormatter));
    }

    public static Optional<BigDecimal> toBigDecimal(String str) {
        if (str == null || str.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new BigDecimal(str));
    }
}
