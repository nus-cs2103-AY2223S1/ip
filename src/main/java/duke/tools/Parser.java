package duke.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/**
 * This class makes sense of the user's input commands.
 * Text commands from the user are being understood and translated into instructions sets for Duke.
 */
public class Parser {

    /** The user's input date and time format. */
    private static final DateTimeFormatter DT_PARSE_FORMAT = DateTimeFormatter.ofPattern("[yyyy][yy]-M-d HHmm");
    /** The date and time format printed out by Duke. */
    private static final DateTimeFormatter DT_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, KK:mm a");

    /**
     * Returns the Command representing the user's input.
     *
     * @param str The input String from the user.
     * @return The Command representing the user's input.
     * @throws DukeException
     */
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

    /**
     * Returns the index of the Task the user tries to access.
     *
     * @param str The input String from the user.
     * @return The index of the Task the user tries to access.
     */
    private static int parseTaskIndex(String str) {
        return Integer.parseInt(str) - 1;
    }

    /**
     * Makes sense of the user's input to add a task with deadline.
     *
     * @param str The input String from the user specifying the parameters of the task with deadline.
     * @return The DeadLineCommand representing the user's input
     * @throws DukeException
     */
    private static DeadlineCommand parseDeadlineCommand(String str) throws DukeException {
        try {
            String[] descDateTime = str.split("/by", 2);
            return new DeadlineCommand(descDateTime[0].strip(), parseDateTime(descDateTime[1].strip()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Insufficient command parameters.");
        }
    }

    /**
     * Makes sense of the user's input to add an event.
     *
     * @param str The input String from the user specifying the parameters of the event.
     * @returnThe EventCommand representing the user's input
     * @throws DukeException
     */
    private static EventCommand parseEventCommand(String str) throws DukeException {
        try {
            String[] descDateTime = str.split("/at", 2);
            return new EventCommand(descDateTime[0].strip(), parseDateTime(descDateTime[1].strip()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Insufficient command parameters.");
        }
    }

    /**
     * Makes sense of the user's input date and time.
     *
     * @param str The input String from the user specifying the date and time.
     * @return A LocalDateTime object representing the user specified date and time.
     * @throws DukeException
     */
    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(str, DT_PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Exception: Cannot parse datetime.");
        }
    }

    /**
     * Returns a String representation of the date and time.
     *
     * @param dateTime The date and time to be formatted.
     * @return A String representation of the date and time.
     */
    public static String formatDateTimeToPrint(LocalDateTime dateTime) {
        return dateTime.format(DT_PRINT_FORMAT);
    }
}
