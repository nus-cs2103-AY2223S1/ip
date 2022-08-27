package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateTimeConverter {
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter DATE_FORMAT_PRINT = DateTimeFormatter
            .ofPattern("MMM dd yyyy");


    public static LocalDate formatDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMAT);
        return date;
    }

    public static String formatString(LocalDate date) {
        return date.format(DATE_FORMAT_PRINT);
    }
}
