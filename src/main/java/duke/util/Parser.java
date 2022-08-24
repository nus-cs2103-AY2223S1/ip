package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to parse user input.
 */
public class Parser {
    private static final String DEADLINE_INDICATOR = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR = "\\s*/at\\s*";

    /**
     * Parses an input string into a {@code Command} object.
     *
     * @param input User input string.
     * @return {@code Command} object.
     * @throws DukeException Checked exceptions that may occur when parsing user input into {@code Command}.
     */
    public static Command parseCommand(String input) throws DukeException {
        input = input.strip();
        String inputCommand = input.indexOf(" ") == -1
                ? input.toLowerCase() : input.toLowerCase().substring(0, input.indexOf(" "));
        String[] commandAndArguments = input.split(" ", 2);
        String args = "";
        if (commandAndArguments.length > 1) {
            args = commandAndArguments[1].strip();
        }

        switch (inputCommand) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            verifyArgumentsIsNotEmpty(args);
            return new MarkCommand(parseTaskIndex(args));
        case "unmark":
            verifyArgumentsIsNotEmpty(args);
            return new UnmarkCommand(parseTaskIndex(args));
        case "todo":
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseTodoArguments(args));
        case "event":
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseEventArguments(args));
        case "deadline":
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseDeadlineArguments(args));
        case "delete":
            verifyArgumentsIsNotEmpty(args);
            return new DeleteCommand(parseTaskIndex(args));
        case "":
            return new EmptyCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static void verifyArgumentsIsNotEmpty(String args) throws DukeException {
        if (args == "") {
            throw new DukeException("Invalid command format, no argument provided");
        }
    }

    private static int parseTaskIndex(String args) throws DukeException {
        String parseErrorMessage = "Please provide an integer for task index";
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(parseErrorMessage);
        }
    }

    private static Task parseTodoArguments(String args) throws DukeException {
        try {
            return new Todo(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid format for todo command");
        }
    }

    private static Task parseDeadlineArguments(String args) throws DukeException {
        String formatErrorMessage = "Invalid format for deadline command";
        String[] arguments = parseArgumentsWithDelimiter(args, DEADLINE_INDICATOR, formatErrorMessage);
        return new Deadline(arguments[0], arguments[1]);
    }

    private static Task parseEventArguments(String args) throws DukeException {
        String formatErrorMessage = "Invalid format for event command";
        String[] arguments = parseArgumentsWithDelimiter(args, EVENT_INDICATOR, formatErrorMessage);
        return new Event(arguments[0], arguments[1]);
    }

    private static String[] parseArgumentsWithDelimiter(String args, String delimiter,
                                                        String formatErrorMessage) throws DukeException {
        String[] arguments = args.split(delimiter, 2);
        if (arguments.length <= 1) {
            throw new DukeException(formatErrorMessage);
        }
        return stripArguments(arguments);
    }

    private static String[] stripArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].strip();
        }
        return args;
    }

    /**
     * Parses a date string into a {@code LocalDate} object.
     *
     * @param dateString date string to parse.
     * @return {@code LocalDate} object.
     * @throws DukeException Checked exceptions that may occur when parsing date string into {@code LocalDate}.
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format, please enter yyyy-mm-dd."
                    + System.lineSeparator() + "Example: 2022-08-22, 2022-12-02");
        }
    }
}
