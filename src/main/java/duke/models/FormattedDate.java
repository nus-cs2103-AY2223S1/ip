package duke.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class FormattedDate {
    LocalDateTime formatDate;
    DateTimeFormatter parserOptionalFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .toFormatter();

    public FormattedDate(String str) {
        this.formatDate = LocalDateTime.parse(str, parserOptionalFormats);
    }

    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}