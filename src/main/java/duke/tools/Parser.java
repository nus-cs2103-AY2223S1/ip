package duke.tools;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class deals with making sense of the user command.
 * Parses text command from user into instructions understood by Duke.
 */
public class Parser {
    /** Date format for user input, and format for date stored in storage */
    private static final DateTimeFormatter DATE_PARSE_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");
    /** Date and time format for user input, and format for date and time stored in storage */
    private static final DateTimeFormatter DATETIME_PARSE_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy");
    /** Date format for message printed by Duke */
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter
            .ofPattern("hh:mma MMM dd yyyy");
    /** Date and time format for message printed by Duke */
    private static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter
            .ofPattern("MMM dd yyyy");

    /**
     * Returns command representing user input.
     *
     * @param str Input string.
     * @return Command representing user input.
     * @throws DukeException If user input is invalid.
     */
    public static Command parseCommand(String str) throws DukeException {
        try {
            String[] input = str.split(" ", 2);
            switch (input[0]) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseIndex(input[1].strip()));
            case "unmark":
                return new UnmarkCommand(parseIndex(input[1].strip()));
            case "delete":
                return new DeleteCommand(parseIndex(input[1].strip()));
            case "todo":
                return new TodoCommand(input[1].strip());
            case "deadline":
                return parseForDeadline(input[1].strip());
            case "event":
                return parseForEvent(input[1].strip());
            default:
                throw new DukeException("OOPS!!! "
                        + "I'm sorry, but I don't know what that means :(");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! "
                    + "Wrong command parameters!");
        }
    }

    /**
     * Parses string of user input into index of task.
     *
     * @param str Input string.
     * @return Index of task.
     */
    private static int parseIndex(String str) {
        return Integer.parseInt(str) - 1;
    }

    /**
     * Parses user input to add a deadline task.
     *
     * @param str Input string from user specifying deadline parameters.
     * @return Deadline command representing user input.
     * @throws DukeException If date format is invalid.
     */
    private static DeadlineCommand parseForDeadline(String str) throws DukeException {
        String[] descDate = str.split("/by", 2);
        return new DeadlineCommand(descDate[0].strip(),
                parseDate(descDate[1].strip()));

    }

    /**
     * Parses user input to add an event task.
     *
     * @param str Input string from user specifying event parameters.
     * @return Event command representing user input.
     * @throws DukeException If date time format is invalid.
     */
    private static EventCommand parseForEvent(String str) throws DukeException {
        String[] descDateTime = str.split("/at", 2);
        return new EventCommand(descDateTime[0].strip(),
                parseDateTime(descDateTime[1].strip()));
    }

    /**
     * Parses user input of date.
     *
     * @param str Input string from user specifying date.
     * @return LocalDate object representing user input of date.
     * @throws DukeException If date format is invalid.
     */
    public static LocalDate parseDate(String str) throws DukeException {
        try {
            return LocalDate.parse(str, DATETIME_PARSE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date format:\n"
                    + "day/month/year > dd/mm/yyyy");
        }
    }

    /**
     * Returns string representation of date in print format.
     *
     * @param date Date to be formatted.
     * @return String representation of date.
     */
    public static String formatDateToPrint(LocalDate date) {
        return date.format(DATETIME_PRINT_FORMAT);
    }

    /**
     * Returns string representation of date in data format.
     *
     * @param date Date to be formatted.
     * @return Data representation of date.
     */
    public static String formatDateToData(LocalDate date) {
        return date.format(DATETIME_PARSE_FORMAT);
    }

    /**
     * Parses user input of date and time.
     *
     * @param str Input string from user specifying date and time.
     * @return LocalDateTime object representing user input of date and time.
     * @throws DukeException If date and time format is invalid.
     */
    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            return LocalDateTime.parse(str, DATE_PARSE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time format:\n"
                    + "day/month/year 24hour time > dd/mm/yyyy HH:mm");
        }
    }

    /**
     * Returns string representation of date and time in print format.
     *
     * @param dateTime Date and time to be formatted.
     * @return String representation of date and time.
     */
    public static String formatDateTimeToPrint(LocalDateTime dateTime) {
        return dateTime.format(DATE_PRINT_FORMAT);
    }

    /**
     * Returns string representation of date and time in data format.
     *
     * @param dateTime Date and time to be formatted.
     * @return Data representation of date and time.
     */
    public static String formatDateTimeToData(LocalDateTime dateTime) {
        return dateTime.format(DATE_PARSE_FORMAT);
    }
}
