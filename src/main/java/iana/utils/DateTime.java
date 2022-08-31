package iana.utils;

import iana.exception.IanaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility to convert tasks' date time format into a standardized format.
 */
public class DateTime {
    private static final DateTimeFormatter INPUT_FORMAT_1 = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
    
    /**
     * Parse date time string.
     * @param timeInput date time input to be parsed.
     * @return string representation of date time in standardized format.
     */
    public static String parseToString(String timeInput) {
        try {
            return OUTPUT_FORMAT.format(parseToLocalDateTime(timeInput));
        } catch (IanaException e) {
            return timeInput;
        }
    }

    /**
     * Parse date time string.
     * @param timeInput date time input to be parsed.
     * @return localdatetime representation of date time in standardized format.
     * @throws IanaException
     */
    public static LocalDateTime parseToLocalDateTime(String timeInput) throws IanaException {
        LocalDateTime timeOutput;

        try {
             timeOutput = LocalDateTime.parse(timeInput, INPUT_FORMAT_1);
        } catch (DateTimeParseException e) {
            throw new IanaException("Wrong date time format!!");
        }

        return timeOutput;
    }
}