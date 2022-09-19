package duke.utils;

import duke.exceptions.UnrecognisedDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is responsible for parsing strings representing dates.
 */
public class DateParser {

    private static DateTimeFormatter[] formatterList = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    };

    /**
     * Parses the given String to a LocalDate.
     * Accepts multiple formats.
     * @param s String representing the date.
     * @return LocalDate corresponding to the given string.
     * @throws UnrecognisedDateException If the given string does not follow any defined format.
     */
    public static LocalDate stringToDate(String s) throws UnrecognisedDateException {
        for (DateTimeFormatter formatter : formatterList) {
            try {
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
            }
        }
        throw new UnrecognisedDateException();
    }

}
