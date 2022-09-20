package duke.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

/** Handles reading of user commands and string manipulation */
public class Parser {

    /** Points to the current word being read in the current command */
    private static int currWordIndex = 0;

    /**
     * Converts the command to an array of words split by " ".
     *
     * @param command User's inputted command
     * @return An array of the command's words, split by " ".
     */
    public static String[] convertToWords(String command) {
        return Arrays.stream(command.strip().split(" ")).toArray(String[]::new);
    }

    /**
     * Retrieves the description argument in the command
     * @param words The words of the command entered, first is some valid command name
     * @param stop The word before which the description ends
     * @return The description specified in words
     * @throws IllegalArgumentException If words specifies an empty description
     */
    public static String getDescription(String[] words, String stop) {
        currWordIndex = 1;
        StringBuilder descBuilder = new StringBuilder();
        boolean descIsEmpty = true;

        while (currWordIndex < words.length && !words[currWordIndex].equals(stop)) {
            if (words[currWordIndex].isEmpty()) {
                descBuilder.append(" ");
            } else {
                descBuilder.append(words[currWordIndex]).append(" ");
                descIsEmpty = false;
            }
            ++currWordIndex;
        }

        if (descIsEmpty) {
            throw new IllegalArgumentException("OOPS!!! Description can't be empty");
        }

        return descBuilder.deleteCharAt(descBuilder.length() - 1).toString(); //remove last whitespace
    }

    /**
     * Retrieves the timing argument in the command, which must be of the form d/M/yyyy
     * followed by an optional (h:mm)am/pm
     * @param words The words of the command entered, first is some valid command name
     * @param flag The flag that the timing belongs to
     * @return The timing specified in words
     * @throws IllegalArgumentException If words is missing flag or the timing is empty/incorrect format
     */
    public static String getTiming(String[] words, String flag) {
        if (currWordIndex >= words.length) {
            throw new IllegalArgumentException("OOPS!!! " + flag + " not found");
        } else if (currWordIndex == words.length - 1) {
            throw new IllegalArgumentException("OOPS!!! Timing for " + flag + " can't be empty");
        }

        ++currWordIndex;
        try {
            return (currWordIndex == words.length - 1)
                    ? reformatDate(words[currWordIndex], "d/M/yyyy", "d MMM yyyy")
                    : reformatDateTime(words[currWordIndex] + " " + words[++currWordIndex],
                    "d/M/yyyy h:mma", "d MMM yyyy, h:mma");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! I don't understand that date or time");
        }
    }

    /**
     * Converts the date into a LocalDate
     * @param date The date to convert
     * @param format The date format
     * @return The date as a LocalDate
     * @throws IllegalArgumentException If format is incorrect
     */
    public static LocalDate convertToLocalDate(String date, String format) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(format)
                .toFormatter(Locale.getDefault());
        return LocalDate.parse(date, formatter);
    }

    /**
     * Converts the date to a new format
     * @param date The date to reformat
     * @param inFormat The current format
     * @param outFormat The new format
     * @return The reformatted date
     * @throws IllegalArgumentException If format is incorrect
     */
    public static String reformatDate(String date, String inFormat, String outFormat) {
        return convertToLocalDate(date, inFormat)
                .format(DateTimeFormatter.ofPattern(outFormat, Locale.getDefault()));
    }

    /**
     * Converts the datetime into a LocalDateTime
     * @param dateTime The datetime to convert
     * @param format The datetime format
     * @return The datetime as a LocalDateTime
     * @throws IllegalArgumentException If format is incorrect
     */
    public static LocalDateTime convertToLocalDateTime(String dateTime, String format) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(format)
                .toFormatter(Locale.getDefault());
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Converts the datetime to a new format
     * @param dateTime The datetime to reformat
     * @param inFormat The current format
     * @param outFormat The new format
     * @return The reformatted datetime
     * @throws IllegalArgumentException If datetime has incorrect format
     */
    public static String reformatDateTime(String dateTime, String inFormat, String outFormat) {
        return convertToLocalDateTime(dateTime, inFormat)
                .format(DateTimeFormatter.ofPattern(outFormat, Locale.getDefault()));
    }

    /**
     * Gets the task number (integer pointing to a task) specified in the command
     *
     * @param words The words of the command entered, first is always some valid command name
     * @return The task number specified
     * @throws IllegalArgumentException If words has an invalid number of arguments or invalid argument value
     */
    public static int getTaskNumber(String[] words) {
        if (TaskList.getTasks().size() == 0) {
            throw new IllegalArgumentException("OOPS!!! No tasks stored for me to do that");
        }

        int taskNumber = 0;

        try {
            taskNumber = (words.length == 2) ? Integer.parseInt(words[1]) : 0;
            if (taskNumber <= 0 || taskNumber > TaskList.getTasks().size()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("OOPS!!! The task number must be from 1 to "
                    + TaskList.getTasks().size());
        }

        return taskNumber;
    }
}
