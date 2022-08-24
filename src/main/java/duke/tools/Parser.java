package duke.tools;

import duke.commands.*;
import duke.exceptions.DukeException;

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
            case "todo":
                return new TodoCommand(inputLine[1].strip());
            case "deadline":
                return parseDeadlineCommand(inputLine[1].strip());
            case "event":
                return parseEventCommand(inputLine[1].strip());
            default:
                throw new DukeException("Exception: Unknown command.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Exception: Incorrect command parameters.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Insufficient command parameters.");
        }
    }

    private static int parseTaskIndex(String str) {
        return Integer.parseInt(str) - 1;
    }

    private static DeadlineCommand parseDeadlineCommand(String str) throws DukeException {
        try {
            String[] descDateTime = str.split("/by", 2);
            return new DeadlineCommand(descDateTime[0].strip(), parseDateTime(descDateTime[1].strip()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Insufficient command parameters.");
        }
    }

    private static EventCommand parseEventCommand(String str) throws DukeException {
        try {
            String[] descDateTime = str.split("/at", 2);
            return new EventCommand(descDateTime[0].strip(), parseDateTime(descDateTime[1].strip()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Insufficient command parameters.");
        }
    }

    private static final DateTimeFormatter DT_PARSE_FORMAT = DateTimeFormatter.ofPattern("[yyyy][yy]-M-d HHmm");
    private static final DateTimeFormatter DT_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, KK:mm a");

    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(str, DT_PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Exception: Cannot parse datetime.");
        }
    }

    public static String formatDateTimeToPrint(LocalDateTime dateTime) {
        return dateTime.format(DT_PRINT_FORMAT);
    }
}
