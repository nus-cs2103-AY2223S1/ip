package duke.tools;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter DATE_PARSE_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATETIME_PARSE_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter
            .ofPattern("hh:mma MMM dd yyyy");
    private static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter
            .ofPattern("MMM dd yyyy");

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
            case "find":
                return new FindCommand(input[1].strip());
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

    private static int parseIndex(String str) {
        return Integer.parseInt(str) - 1;
    }

    private static DeadlineCommand parseForDeadline(String str) throws DukeException {
        String[] descDate = str.split("/by", 2);
        return new DeadlineCommand(descDate[0].strip(),
                parseDate(descDate[1].strip()));

    }

    private static EventCommand parseForEvent(String str) throws DukeException {
        String[] descDateTime = str.split("/at", 2);
        return new EventCommand(descDateTime[0].strip(),
                parseDateTime(descDateTime[1].strip()));
    }

    public static LocalDate parseDate(String str) throws DukeException {
        try {
            LocalDate dateTime = LocalDate.parse(str, DATETIME_PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Please enter a valid date format:\n"
                    + "day/month/year > dd/mm/yyyy");
        }
    }

    public static String formatDateToPrint(LocalDate date) {
        return date.format(DATETIME_PRINT_FORMAT);
    }

    public static String formatDateToData(LocalDate date) {
        return date.format(DATETIME_PARSE_FORMAT);
    }

    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(str, DATE_PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Please enter a valid date and time format:\n"
                    + "day/month/year 24 hour time > dd/mm/yyyy HH:mm");
        }
    }

    public static String formatDateTimeToPrint(LocalDateTime dateTime) {
        return dateTime.format(DATE_PRINT_FORMAT);
    }

    public static String formatDateTimeToData(LocalDateTime dateTime) {
        return dateTime.format(DATE_PARSE_FORMAT);
    }
}
