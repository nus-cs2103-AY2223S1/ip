import java.util.Scanner;
import java.util.Optional;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public abstract class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String DEADLINE_INDICATOR_PATTERN = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR_PATTERN = "\\s*/at\\s*";

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

    static String getCommandArgument(Optional<String> argument) throws DukeException {
        return argument.orElseThrow(() -> new DukeException("Required arguments not found!"));
    }

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
        default:
            command = parseUnknown(commandIndicator);
            break;
        }
        return command;
    }

    static Command parseBye() {
        return new ByeCommand();
    }

    static Command parseList() {
        return new ListCommand();
    }

    static Command parseTodo(String input) {
        Task task = new Todo(input);
        return new AddCommand(task);
    }

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

    static Command parseMark(String input) throws DukeException {
        int index = parseIndex(input);
        return new MarkCommand(index);
    }

    static Command parseUnmark(String input) throws DukeException {
        int index = parseIndex(input);
        return new UnmarkCommand(index);
    }

    static Command parseDelete(String input) throws DukeException {
        int index = parseIndex(input);
        return new DeleteCommand(index);
    }

    static Command parseUnknown(String input) {
        return new UnknownCommand(input);
    }

    private static int parseIndex(String input) throws DukeException {
        try (Scanner scanner = new Scanner(input)) {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            throw new DukeException("The index must be an Integer!", e);
        } catch (NoSuchElementException e) {
            throw new DukeException("No index detected!", e);
        }
    }
}
