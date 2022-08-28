package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class FormattedDate {
    LocalDate formatDate;
    DateTimeFormatter parserOptionalFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            .toFormatter();

    public FormattedDate(String str) {
        this.formatDate = LocalDate.parse(str, parserOptionalFormats);
    }

    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}