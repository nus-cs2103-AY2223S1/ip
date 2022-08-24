package utils;

import exceptions.InvalidDateException;

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
     * Convert a string from the formats listed below into a date format
     *
     * @param dateString String that we want to convert to a date.
     * @return a date object representing the dateString.
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
     * Convert a date into a string format
     *
     * @param date Date that we want to convert to a string.
     * @return a string representing the date.
     */
    public static String formatDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
