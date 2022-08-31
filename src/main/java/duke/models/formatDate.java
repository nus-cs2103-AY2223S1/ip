package duke.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class formatDate {
    LocalDate formattedDate;
    DateTimeFormatter formats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            .toFormatter();

    public formatDate(String date) {
        this.formattedDate = LocalDate.parse(date, formats);
    }

    @Override
    public String toString() {
        return this.formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
