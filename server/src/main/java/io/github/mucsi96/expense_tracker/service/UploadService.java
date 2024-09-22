package io.github.mucsi96.expense_tracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.mucsi96.expense_tracker.converter.AccountStatementConverter;
import io.github.mucsi96.expense_tracker.converter.CardStatementConverter;
import io.github.mucsi96.expense_tracker.enums.CSVType;
import io.github.mucsi96.expense_tracker.model.AccountStatement;
import io.github.mucsi96.expense_tracker.model.CardStatement;

@Service
public class UploadService {
    List<String> supportedHeaders = List.of(
            "Trade date;Trade time;Booking date;Value date;Currency;Debit;Credit;Individual amount;Balance;Transaction no.;Description1;Description2;Description3;Footnotes;",
            "Account number;Card number;Account/Cardholder;Purchase date;Booking text;Sector;Amount;Original currency;Rate;Currency;Debit;Credit;Booked");

    public Optional<CSVType> detectCSVType(MultipartFile file) {
        CSVFormat csvFormat = CSVFormat.EXCEL.builder().setDelimiter(";").build();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                CSVParser csvParser = new CSVParser(bufferedReader, csvFormat);) {

            if (csvParser.getRecords().stream().map(record -> String.join(";", record.values()))
                    .anyMatch(header -> header.equals(CSVType.CARD_STATEMENT.getHeader()))) {
                return Optional.of(CSVType.CARD_STATEMENT);
            }

            if (csvParser.getRecords().stream().map(record -> String.join(";", record.values()))
                    .anyMatch(header -> header.equals(CSVType.CARD_STATEMENT.getHeader()))) {
                return Optional.of(CSVType.CARD_STATEMENT);
            }

            return Optional.empty();

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public List<CardStatement> parseCardStatement(MultipartFile file) {
        CSVFormat csvFormat = CSVFormat.EXCEL.builder().setDelimiter(";").build();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                CSVParser csvParser = new CSVParser(bufferedReader, csvFormat);) {

            return csvParser.getRecords().stream()
                    .map(CardStatementConverter::fromCSVRecord)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (IOException e) {
            return List.of();
        }
    }

    public List<AccountStatement> parseAccountStatement(MultipartFile file) {
        CSVFormat csvFormat = CSVFormat.EXCEL.builder().setDelimiter(";").build();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                CSVParser csvParser = new CSVParser(bufferedReader, csvFormat);) {

            return csvParser.getRecords().stream()
                    .map(AccountStatementConverter::fromCSVRecord)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (IOException e) {
            return List.of();
        }
    }
}
