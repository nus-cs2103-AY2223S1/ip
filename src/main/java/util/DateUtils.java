package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import exceptions.HenryException;

/**
 * Utility class containing DateTime related methods
 */
public class DateUtils {

    private static final DateTimeFormatter parseFormatter =
        new DateTimeFormatterBuilder().appendPattern("[dd-MM-yyyy HH:mm]")
                                      .appendPattern("[dd/MM/yyyy HH:mm]")
                                      .appendPattern("[dd-MM-yyyy h:mm a]")
                                      .appendPattern("[dd MMM yyyy HH:mm]")
                                      .toFormatter();

    private static final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a");

    /**
     * Parses a String into a LocalDateTime object.
     *
     * @param input the String to be parsed
     * @return a LocalDateTime object representing the input String
     */
    public static LocalDateTime parseDateTime(String input) {
        try {
            LocalDateTime parsed = LocalDateTime.parse(input, parseFormatter);
            if (!isDateValid(parsed)) {
                throw new HenryException(TextUtils.DATE_IN_PAST_ERROR);
            }
            return parsed;
        } catch (Exception e) {
            throw new HenryException(TextUtils.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Parses a list of LocalDateTime objects from the given String input
     * and adds them to a List. Any LocalDateTime objects in the past
     * are not added.
     *
     * @param input the String to be converted into a List of LocalDateTime objects
     * @return a List of LocalDateTime objects representing the input
     */
    public static List<LocalDateTime> parseMultipleDateTimes(String input) {
        List<LocalDateTime> dates = new ArrayList<>();
        String[] tokens = input.split(",");
        LocalDateTime now = LocalDateTime.now();

        for (String token : tokens) {
            LocalDateTime date = parseDateTime(token.trim());
            if (date.isAfter(now)) {
                dates.add(date);
            }
        }

        return dates;
    }

    /**
     * Converts a LocalDateTime object to a String with "dd-MM-yyyy h:mm a" format.
     *
     * @param date the LocalDateTime object to be converted
     * @return a String representation of the LocalDateTime object
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(fileFormatter);
    }

    private static boolean isDateValid(LocalDateTime dateTime) {
        return !dateTime.isBefore(LocalDateTime.now());
    }
}
