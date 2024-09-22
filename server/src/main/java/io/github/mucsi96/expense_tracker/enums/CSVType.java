package io.github.mucsi96.expense_tracker.enums;

public enum CSVType {
    CARD_STATEMENT(
            "Account number;Card number;Account/Cardholder;Purchase date;Booking text;Sector;Amount;Original currency;Rate;Currency;Debit;Credit;Booked"),
    ACCOUNT_STATEMENT(
            "Trade date;Trade time;Booking date;Value date;Currency;Debit;Credit;Individual amount;Balance;Transaction no.;Description1;Description2;Description3;Footnotes;");

    private final String header;

    CSVType(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
