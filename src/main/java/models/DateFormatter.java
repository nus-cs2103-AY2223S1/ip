package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    LocalDate formatDate;

    public DateFormatter(String str) {
        this.formatDate = LocalDate.parse(str);
    }

    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}