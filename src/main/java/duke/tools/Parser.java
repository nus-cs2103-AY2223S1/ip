package duke.tools;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String str) throws DukeException {
        try {
            String[] inputLine = str.split(" ", 2);
            switch (inputLine[0]) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskIndex(inputLine[1].strip()));
            case "unmark":
                return new UnmarkCommand(parseTaskIndex(inputLine[1].strip()));
            case "delete":
                return new DeleteCommand(parseTaskIndex(inputLine[1].strip()));
            case "find":
                return new FindCommand(inputLine[1].strip());
            case "todo":
                return new TodoCommand(inputLine[1].strip());
            case "deadline":
                String[] deadlineParam = parseForDeadline(inputLine[1].strip());
                return new DeadlineCommand(deadlineParam[0], parseDateTime(deadlineParam[1]));
            case "event":
                String[] eventParam = parseForEvent(inputLine[1].strip());
                return new EventCommand(eventParam[0], parseDateTime(eventParam[1]));
            default:
                throw new DukeException("Exception: Unknown command.");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Exception: Wrong command parameters.");
        }
    }

    private static int parseTaskIndex(String str) {
        return Integer.parseInt(str) - 1;
    }

    private static String[] parseForDeadline(String str) {
        String[] descDateTime = str.split("/by", 2);
        String[] output = {descDateTime[0].strip(), descDateTime[1].strip()};
        return output;
    }

    private static String[] parseForEvent(String str) {
        String[] descDateTime = str.split("/at", 2);
        String[] output = {descDateTime[0].strip(), descDateTime[1].strip()};
        return output;
    }

    private static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("[yyyy][yy]-M-d HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, KK:mm a");

    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(str, PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Exception: Cannot parse datetime.");
        }
    }

    public static String displayDateTime(LocalDateTime dateTime) {
        return dateTime.format(PRINT_FORMAT);
    }
}
