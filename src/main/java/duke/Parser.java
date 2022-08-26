package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
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
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class used to parse user inputs.
 */
public abstract class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String DEADLINE_INDICATOR_PATTERN = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR_PATTERN = "\\s*/at\\s*";

    /**
     * Parses input into Command indicator and arguments.
     *
     * @param input user input.
     * @return A command indicator and arguments pair.
     * @throws DukeException when error parsing input.
     */
    static Map.Entry<String, Optional<String>> getCommandAndArguments(String input) throws DukeException {
        Scanner scanner = new Scanner(input);
        String indicator;
        try {
            indicator = scanner.next();
        } catch (NoSuchElementException e) {
            scanner.close();
            throw new DukeException("Can't read input!", e);
        }
        try {
            scanner.skip(scanner.delimiter());
            String arguments = scanner.nextLine();
            return new SimpleEntry<>(indicator, Optional.of(arguments));
        } catch (NoSuchElementException e) {
            return new SimpleEntry<>(indicator, Optional.empty());
        } finally {
            scanner.close();
        }
    }

    /**
     * Retrieves Optional argument or throws DukeException if Optional is empty.
     *
     * @param argument Arugment wrapped in Optional.
     * @return Argument in the Optional.
     * @throws DukeException When argument is empty.
     */
    static String getCommandArgument(Optional<String> argument) throws DukeException {
        return argument.orElseThrow(() -> new DukeException("Required arguments not found!"));
    }

    /**
     * Parses input into corresponding Command object.
     *
     * @param input User input.
     * @return Command object.
     * @throws DukeException When error in parsing or executing command.
     */
    static Command parse(String input) throws DukeException {
        Map.Entry<String, Optional<String>> commandString = getCommandAndArguments(input);
        String commandIndicator = commandString.getKey();
        Optional<String> commandArguments = commandString.getValue();

        Command command;
        switch (commandIndicator) {
        case BYE:
            command = parseBye();
            break;
        case LIST:
            command = parseList();
            break;
        case TODO:
            command = parseTodo(getCommandArgument(commandArguments));
            break;
        case DEADLINE:
            command = parseDeadline(getCommandArgument(commandArguments));
            break;
        case EVENT:
            command = parseEvent(getCommandArgument(commandArguments));
            break;
        case MARK:
            command = parseMark(getCommandArgument(commandArguments));
            break;
        case UNMARK:
            command = parseUnmark(getCommandArgument(commandArguments));
            break;
        case DELETE:
            command = parseDelete(getCommandArgument(commandArguments));
            break;
        case FIND:
            command = parseFind(getCommandArgument(commandArguments));
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
     * @throws DukeException when command arugments are invalid.
     */
    static Command parseDeadline(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(DEADLINE_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String by = lineScanner.next();
            Task task = new Deadline(description, by);
            return new AddCommand(task);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding deadline!", e);
        }
    }

    /**
     * Returns an AddCommand that adds an Event.
     *
     * @param input command arguments.
     * @return AddCommand.
     * @throws DukeException when command arugments are invalid.
     */
    static Command parseEvent(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(EVENT_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String at = lineScanner.next();
            Task task = new Event(description, at);
            return new AddCommand(task);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding event!", e);
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
     * @param input command arugments.
     * @return index given by user.
     * @throws DukeException when index not an integer or no index detected.
     */
    private static int parseIndex(String input) throws DukeException {
        try (Scanner scanner = new Scanner(input)) {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            throw new DukeException("The index must be an Integer!", e);
        } catch (NoSuchElementException e) {
            throw new DukeException("No index detected!", e);
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
            throw new DukeException("Date format is invalid!", e);
        }
    }

}
