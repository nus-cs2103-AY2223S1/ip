package duke.utils;

import duke.exceptions.UnrecognisedDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static DateTimeFormatter[] formatterList = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    };

    public static LocalDate stringToDate(String s) throws UnrecognisedDateException {
        for (DateTimeFormatter formatter : formatterList) {
            try {
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {}
        }
        throw new UnrecognisedDateException();
    }

}
