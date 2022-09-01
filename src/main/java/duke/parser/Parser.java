package duke.parser;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

import duke.dukeexception.DateTimeFormatException;

/**
 * The parser class helps to convert user input into correct format.
 */
public class Parser {
    /**
     * Converts integer string to integer.
     * @param str The integer string.
     * @return The integer implied.
     */
    public static Integer strToInt(String str) {
        Integer result = null;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Conversion from String to Integer failed. Please check!");
        }
        return result;
    }


    /**
     * Detects the commands that needs more than 1 variables.
     * @param str The command input by user.
     * @return A boolean value to indicate if the command needs another input.
     */
    public static boolean hasMultipleVariables(String str) {
        String[] commands = {"mark", "unmark", "todo", "deadline", "event", "delete"};
        HashSet<String> checkList = new HashSet<String>(Arrays.asList(commands));
        return checkList.contains(str.toLowerCase());
    }

    /**
     * Converts a datetime string to a LocalDateTime Object.
     * @param dateTime A string in format: yyyy-MM-dd hh:mm.
     * @return A LocalDateTime variable implied by the string.
     * @throws DateTimeFormatException
     */
    public static LocalDateTime strToDateTime(String dateTime) throws DateTimeFormatException {
        String[] dateAndTime = dateTime.trim().split(" ");
        String hour;
        String min;

        if (dateAndTime.length != 2) {
            throw new DateTimeFormatException("The correct Format: YYYY-MM-DD HH:mm");
        } else {
            String[] components = dateAndTime[1].strip().split(":", 2);
            if (components.length != 2) {
                throw new DateTimeFormatException("The correct Format: HH:MM");
            } else {
                hour = components[0];
                min = components[1];
                if (hour.length() == 2 && min.length() == 2) {
                    try {
                        LocalDate date = strToDate(dateAndTime[0]);
                        LocalTime time = LocalTime.parse(dateAndTime[1]);
                        return LocalDateTime.of(date, time);
                    } catch (DateTimeException e) {
                        throw new DateTimeFormatException("The correct Format: YYYY-MM-DD");
                    }
                } else {
                    throw new DateTimeFormatException("The correct Format: YYYY-MM-DD");
                }
            }
        }
    }

    /**
     * Converts a datetime string to a LocalDate Object.
     * @param date A string in format: yyyy-MM-dd.
     * @return A LocalDate variable implied by the string.
     * @throws DateTimeFormatException
     */
    public static LocalDate strToDate(String date) throws DateTimeFormatException {
        String[] components = date.strip().split("-", 3);
        String year;
        String month;
        String day;
        if (components.length != 3) {
            throw new DateTimeFormatException("The correct Format: YYYY-MM-DD");
        } else {
            year = components[0];
            month = components[1];
            day = components[2];
            if (year.length() == 4 && month.length() == 2 && day.length() == 2) {
                try {
                    return LocalDate.parse(date);
                } catch (DateTimeException e) {
                    throw new DateTimeFormatException("The correct Format: YYYY-MM-DD");
                }
            } else {
                throw new DateTimeFormatException("The correct Format: YYYY-MM-DD");
            }
        }
    }

    public static String dateTimeToString(LocalDateTime dateTime) throws DateTimeFormatException {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public static String dateToString(LocalDate date) throws DateTimeFormatException {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
