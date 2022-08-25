package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattedDate {
    LocalDate formatDate;

    public FormattedDate(String str) {
        this.formatDate = LocalDate.parse(str);
    }

    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}