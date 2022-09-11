package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.FindCommand;
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
    private static final String INDICATOR_DEADLINE = "\\s*/by\\s*";
    private static final String INDICATOR_EVENT = "\\s*/at\\s*";

    private static final int LIMIT_SPLIT_STRING_INTO_TWO_PARTS = 2;

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EMPTY = "";

    private static final String ERROR_MESSAGE_ARGUMENTS_EMPTY = "Invalid command format, no argument provided";
    private static final String ERROR_MESSAGE_NO_INTEGER_INDEX_PROVIDED = "Please provide an integer for task index";
    private static final String ERROR_MESSAGE_INVALID_TASK_FORMAT_TODO = "Invalid format for todo command";
    private static final String ERROR_MESSAGE_INVALID_TASK_FORMAT_DEADLINE = "Invalid format for deadline command";
    private static final String ERROR_MESSAGE_INVALID_TASK_FORMAT_EVENT = "Invalid format for event command";
    private static final String ERROR_MESSAGE_INVALID_DATE_STRING_FORMAT =
            "Invalid date format, please enter yyyy-mm-dd."
                    + System.lineSeparator() + "Example: 2022-08-22, 2022-12-02";

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

        if (COMMAND_EMPTY.equals(inputCommand)) {
            return new EmptyCommand();
        } else if (COMMAND_HELP.startsWith(inputCommand)) {
            return new HelpCommand();
        } else if (COMMAND_BYE.startsWith(inputCommand)) {
            return new ByeCommand();
        } else if (COMMAND_LIST.startsWith(inputCommand)) {
            return new ListCommand();
        } else if (COMMAND_MARK.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new MarkCommand(parseTaskIndex(args));
        } else if (COMMAND_UNMARK.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new UnmarkCommand(parseTaskIndex(args));
        } else if (COMMAND_TODO.startsWith((inputCommand))) {
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseTodoArguments(args));
        } else if (COMMAND_EVENT.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseEventArguments(args));
        } else if (COMMAND_DEADLINE.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new AddCommand(parseDeadlineArguments(args));
        } else if (COMMAND_DELETE.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new DeleteCommand(parseTaskIndex(args));
        } else if (COMMAND_FIND.startsWith(inputCommand)) {
            verifyArgumentsIsNotEmpty(args);
            return new FindCommand(args);
        } else {
            return new UnknownCommand();
        }
    }

    private static void verifyArgumentsIsNotEmpty(String args) throws DukeException {
        if (args == "") {
            throw new DukeException(ERROR_MESSAGE_ARGUMENTS_EMPTY);
        }
    }

    private static int parseTaskIndex(String args) throws DukeException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE_NO_INTEGER_INDEX_PROVIDED);
        }
    }

    private static Task parseTodoArguments(String args) throws DukeException {
        try {
            return new Todo(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE_INVALID_TASK_FORMAT_TODO);
        }
    }

    private static Task parseDeadlineArguments(String args) throws DukeException {
        String[] arguments = parseArgumentsWithDelimiter(args, INDICATOR_DEADLINE,
                ERROR_MESSAGE_INVALID_TASK_FORMAT_DEADLINE);
        return new Deadline(arguments[0], arguments[1]);
    }

    private static Task parseEventArguments(String args) throws DukeException {
        String[] arguments = parseArgumentsWithDelimiter(args, INDICATOR_EVENT,
                ERROR_MESSAGE_INVALID_TASK_FORMAT_EVENT);
        return new Event(arguments[0], arguments[1]);
    }

    private static String[] parseArgumentsWithDelimiter(String args, String delimiter,
                                                        String formatErrorMessage) throws DukeException {
        String[] arguments = args.split(delimiter, LIMIT_SPLIT_STRING_INTO_TWO_PARTS);
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
            throw new DukeException(ERROR_MESSAGE_INVALID_DATE_STRING_FORMAT);
        }
    }
}
