package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class used to parse user inputs.
 */
public abstract class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_UPDATE = "update";
    private static final String INDICATOR_PATTERN_DEADLINE = "\\s*/by\\s*";
    private static final String INDICATOR_PATTERN_EVENT = "\\s*/at\\s*";

    private static final String ERROR_MESSAGE_INDEX_NOT_INT = "The index must be an Integer!";
    private static final String ERROR_MESSAGE_INDEX_NOT_FOUND = "No index detected!";
    private static final String ERROR_MESSAGE_DATE_FORMAT_INVALID = "Date format is invalid!";
    private static final String ERROR_MESSAGE_INPUT_READ_ERROR = "Can't read input!";
    private static final String ERROR_MESSAGE_ARGUMENTS_NOT_FOUND = "Required arguments not found!";
    private static final String ERROR_MESSAGE_DEADLINE_FORMAT_INVALID = "Invalid format for adding deadline!";
    private static final String ERROR_MESSAGE_EVENT_FORMAT_INVALID = "Invalid format for adding event!";
    private static final String ERROR_MESSAGE_UPDATE_FORMAT_INVALID = "Invalid format for updating task!";

    /**
     * Parses input into Command indicator and arguments.
     *
     * @param input User input.
     * @return A command indicator and arguments pair.
     * @throws DukeException When error parsing input.
     */
    static Map.Entry<String, String> getCommandAndArguments(String input) throws DukeException {
        try (Scanner scanner = new Scanner(input)) {
            String indicator = parseIndicator(scanner);
            assert indicator.length() > 0;
            String arguments = parseArguments(scanner);
            return new SimpleEntry<>(indicator, arguments);
        } catch (IllegalStateException e) {
            throw new DukeException(ERROR_MESSAGE_INPUT_READ_ERROR, e);
        }
    }

    private static String parseIndicator(Scanner scanner) throws DukeException {
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            scanner.close();
            throw new DukeException(ERROR_MESSAGE_INPUT_READ_ERROR, e);
        }
    }

    private static String parseArguments(Scanner scanner) {
        try {
            scanner.skip(scanner.delimiter());
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Retrieves Optional argument or throws DukeException if Optional is empty.
     *
     * @param argument Argument wrapped in Optional.
     * @return Argument in the Optional.
     * @throws DukeException When argument is empty.
     */
    static String getCommandArgument(String argument) throws DukeException {
        if (argument == null) {
            throw new DukeException(ERROR_MESSAGE_ARGUMENTS_NOT_FOUND);
        }
        return argument;
    }

    /**
     * Parses input into corresponding Command object.
     *
     * @param input User input.
     * @return Command object.
     * @throws DukeException When error in parsing or executing command.
     */
    static Command parse(String input) throws DukeException {
        Map.Entry<String, String> commandString = getCommandAndArguments(input);
        String commandIndicator = commandString.getKey();
        String commandArguments = commandString.getValue();

        Command command;
        switch (commandIndicator) {
        case COMMAND_BYE:
            command = parseBye();
            break;
        case COMMAND_LIST:
            command = parseList();
            break;
        case COMMAND_TODO:
            command = parseTodo(getCommandArgument(commandArguments));
            break;
        case COMMAND_DEADLINE:
            command = parseDeadline(getCommandArgument(commandArguments));
            break;
        case COMMAND_EVENT:
            command = parseEvent(getCommandArgument(commandArguments));
            break;
        case COMMAND_MARK:
            command = parseMark(getCommandArgument(commandArguments));
            break;
        case COMMAND_UNMARK:
            command = parseUnmark(getCommandArgument(commandArguments));
            break;
        case COMMAND_DELETE:
            command = parseDelete(getCommandArgument(commandArguments));
            break;
        case COMMAND_FIND:
            command = parseFind(getCommandArgument(commandArguments));
            break;
        case COMMAND_UPDATE:
            command = parseUpdate(getCommandArgument(commandArguments));
            break;
        default:
            command = parseUnknown(commandIndicator);
            break;
        }
        return command;
    }

    /**
     * Returns a ByeCommand.
     *
     * @return ByeCommand.
     */
    static Command parseBye() {
        return new ByeCommand();
    }

    /**
     * Returns a ListCommand.
     *
     * @return ListCommand.
     */
    static Command parseList() {
        return new ListCommand();
    }

    /**
     * Returns an AddCommand that adds a Todo.
     *
     * @param input Command arguments.
     * @return AddCommand.
     */
    static Command parseTodo(String input) {
        Task task = new Todo(input);
        return new AddCommand(task);
    }

    /**
     * Returns an AddCommand that adds a Deadline.
     *
     * @param input command arguments.
     * @return AddCommand.
     * @throws DukeException when command arguments are invalid.
     */
    static Command parseDeadline(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(INDICATOR_PATTERN_DEADLINE)) {
            String description = lineScanner.next();
            String by = lineScanner.next();
            Task task = new Deadline(description, by);
            return new AddCommand(task);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_DEADLINE_FORMAT_INVALID, e);
        }
    }

    /**
     * Returns an AddCommand that adds an Event.
     *
     * @param input command arguments.
     * @return AddCommand.
     * @throws DukeException when command arguments are invalid.
     */
    static Command parseEvent(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(INDICATOR_PATTERN_EVENT)) {
            String description = lineScanner.next();
            String at = lineScanner.next();
            Task task = new Event(description, at);
            return new AddCommand(task);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_EVENT_FORMAT_INVALID, e);
        }
    }

    /**
     * Returns a MarkCommand
     *
     * @param input command arguments.
     * @return MarkCommand.
     * @throws DukeException when error parsing index or index out of range.
     */
    static Command parseMark(String input) throws DukeException {
        int index = parseIndex(input);
        return new MarkCommand(index);
    }

    /**
     * Returns an UnmarkCommand
     *
     * @param input command arguments.
     * @return UnmarkCommand.
     * @throws DukeException when error parsing index or index out of range.
     */
    static Command parseUnmark(String input) throws DukeException {
        int index = parseIndex(input);
        return new UnmarkCommand(index);
    }

    /**
     * Returns a DeleteCommand.
     *
     * @param input command arguments.
     * @return DeleteCommand.
     * @throws DukeException when error parsing index or index out of range.
     */
    static Command parseDelete(String input) throws DukeException {
        int index = parseIndex(input);
        return new DeleteCommand(index);
    }

    /**
     * Returns a FindCommand.
     *
     * @param input query text.
     * @return FindCommand.
     */
    static Command parseFind(String input) {
        return new FindCommand(input);
    }

    /**
     * Returns an UpdateCommand
     *
     * @param input command arguments.
     * @return UpdateCommand.
     * @throws DukeException when error parsing index or index out of range.
     */
    static Command parseUpdate(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)) {
            int index = parseIndex(lineScanner.next());
            lineScanner.skip(lineScanner.delimiter());
            String description = lineScanner.nextLine();
            return new UpdateCommand(index, description);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_UPDATE_FORMAT_INVALID, e);
        }
    }

    /**
     * Returns an UnknownCommand.
     *
     * @param input command indicator.
     * @return UnknownCommand.
     */
    static Command parseUnknown(String input) {
        return new UnknownCommand(input);
    }

    /**
     * Parses command arguments to task index in 0-based index.
     *
     * @param input command arguments.
     * @return index given by user.
     * @throws DukeException when index not an integer or no index detected.
     */
    private static int parseIndex(String input) throws DukeException {
        try (Scanner scanner = new Scanner(input)) {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            throw new DukeException(ERROR_MESSAGE_INDEX_NOT_INT, e);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_INDEX_NOT_FOUND, e);
        }
    }

    /**
     * Converts date string to LocalDate.
     *
     * @param dateString dateString to convert to LocalDate.
     * @return LocalDate.
     * @throws DukeException when {@code dateString} is in an invalid format.
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_MESSAGE_DATE_FORMAT_INVALID, e);
        }
    }

}
