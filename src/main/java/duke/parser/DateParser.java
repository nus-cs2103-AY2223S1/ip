package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.parser.exceptions.InvalidDateException;

/**
 * Deals with verifying the validity of input date formats and formatting them to be shown to the user.
 */
public class DateParser {
    private static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";

    /**
     * Checks if the given date is in the format: yyyy-MM-dd.
     * @param date The date to check the format for.
     * @throws InvalidDateException If the given date is in the wrong format.
     */
    public static void verifyDateFormat(String date) throws InvalidDateException {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(DATE_INPUT_FORMAT);
        }
    }

    /**
     * Returns a LocalDate object that represents the given date.
     * @param date The date to be parsed into a LocalDate object.
     * @return The LocalDate object representing the given date.
     */
    public static String getParsedDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
