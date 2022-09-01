package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDate stringToDate(String s) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(s, formatter);
    }

}
