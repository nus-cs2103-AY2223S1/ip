package duke.utils;

import duke.exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Utils} class contains useful utilities used throughout the application.
 */
public class Utils {

    /**
     * Returns a boolean indicating if an input can be parsed into an integer
     *
     * @param input String that we want to test
     * @return <code>true</code> if the string cannot be parsed into an integer
     * <code>false</code> otherwise.
     */
    public static boolean isNotParsable(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException numberFormatException) {
            return true;
        }
    }

    /**
     * Returns a localDate object representing the provided date.
     * The format of the date provided is of yyyy-mm-dd.
     *
     * @param dateString a string that we want to convert to a localDate.
     * @return a localDate object representing the dateString.
     */
    public static LocalDate formatStringToDate(String dateString) throws InvalidDateException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException();
        }
        return date;
    }

    /**
     * Returns a string representing the provided localDate.
     *
     * @param date the localDate that we want to convert to a string.
     * @return a string representing the date provided.
     */
    public static String formatDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
