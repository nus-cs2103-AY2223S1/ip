package nyanduke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import nyanduke.command.AddCommand;
import nyanduke.command.Command;
import nyanduke.command.DeleteCommand;
import nyanduke.command.ExitCommand;
import nyanduke.command.FindCommand;
import nyanduke.command.ListCommand;
import nyanduke.command.MarkCommand;
import nyanduke.command.OnCommand;
import nyanduke.command.UnmarkCommand;
import nyanduke.task.Deadline;
import nyanduke.task.Event;
import nyanduke.task.Todo;

/**
 * The Parser class deals with making sense of user commands to NyanDuke.
 */
public class Parser {

    private enum Keyword {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, ON, FIND
    }

    /**
     * Executes the user command specified to NyanDuke.
     *
     * @param fullCommand The command specified to NyanDuke.
     * @return A Command object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    public static Command parse(String fullCommand) throws NyanDukeException {
        assert fullCommand != null : "Parser::parse invoked with null argument.";
        try {
            String[] strings = fullCommand.split(" ");
            String firstCommand = strings[0].toUpperCase();
            Keyword keyword = Keyword.valueOf(firstCommand);

            switch (keyword) {
            case BYE:
                return new ExitCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                return parseMark(fullCommand);

            case UNMARK:
                return parseUnmark(fullCommand);

            case TODO:
                return parseTodo(fullCommand);

            case DEADLINE:
                return parseDeadline(fullCommand);

            case EVENT:
                return parseEvent(fullCommand);

            case DELETE:
                return parseDelete(fullCommand);

            case ON:
                return parseOn(fullCommand);

            case FIND:
                return parseFind(fullCommand);

            default:
                throw new NyanDukeException(Ui.ERROR_UNKNOWN_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            throw new NyanDukeException(Ui.ERROR_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses a Find command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return A FindCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static FindCommand parseFind(String fullCommand) throws NyanDukeException {
        try {
            return new FindCommand(fullCommand.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new NyanDukeException(Ui.ERROR_FIND);
        }
    }

    /**
     * Parses an On command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return An OnCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static OnCommand parseOn(String fullCommand) throws NyanDukeException {
        if (fullCommand.length() == 2) {
            throw new NyanDukeException(Ui.ERROR_ON);
        }
        try {
            LocalDate date = LocalDate.parse(fullCommand.substring(3));
            return new OnCommand(date);
        } catch (DateTimeParseException e) {
            throw new NyanDukeException(Ui.ERROR_ON);
        }
    }

    /**
     * Parses a Delete command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return A DeleteCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static DeleteCommand parseDelete(String fullCommand) throws NyanDukeException {
        if (fullCommand.length() == 6) {
            throw new NyanDukeException(Ui.ERROR_DELETE);
        }
        try {
            String input = fullCommand.substring(7);
            String[] strings = input.split(" ");
            Integer[] numbers = Arrays.stream(strings)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            return new DeleteCommand(numbers);
        } catch (NumberFormatException e) {
            throw new NyanDukeException(Ui.ERROR_DELETE);
        }
    }

    /**
     * Parses an Event command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return An AddCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static AddCommand parseEvent(String fullCommand) throws NyanDukeException {
        try {
            String input = fullCommand.substring(6);
            if (input.startsWith("/at")) {
                throw new NyanDukeException(Ui.ERROR_EVENT_DESCRIPTION);
            }
            String[] splitInput = input.split(" /at ", 2);
            return new AddCommand(new Event(splitInput[0], splitInput[1]));
        } catch (StringIndexOutOfBoundsException | NyanDukeException e) {
            throw new NyanDukeException(Ui.ERROR_EVENT_DESCRIPTION);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NyanDukeException(Ui.ERROR_EVENT_AT);
        }
    }

    /**
     * Parses a Deadline command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return An AddCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static AddCommand parseDeadline(String fullCommand) throws NyanDukeException {
        try {
            String input = fullCommand.substring(9);
            if (input.startsWith("/by")) {
                throw new NyanDukeException(Ui.ERROR_DEADLINE_DESCRIPTION);
            }
            String[] splitInput = input.split(" /by ", 2);
            return new AddCommand(new Deadline(splitInput[0], splitInput[1]));
        } catch (StringIndexOutOfBoundsException e) {
            throw new NyanDukeException(Ui.ERROR_DEADLINE_DESCRIPTION);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NyanDukeException(Ui.ERROR_DEADLINE_BY);
        }
    }

    /**
     * Parses a Todo command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return An AddCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static AddCommand parseTodo(String fullCommand) throws NyanDukeException {
        try {
            return new AddCommand(new Todo(fullCommand.substring(5)));
        } catch (StringIndexOutOfBoundsException e) {
            throw new NyanDukeException(Ui.ERROR_TODO);
        }
    }

    /**
     * Parses an Unmark command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return An UnmarkCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static UnmarkCommand parseUnmark(String fullCommand) throws NyanDukeException {
        if (fullCommand.length() == 6) {
            throw new NyanDukeException(Ui.ERROR_UNMARK);
        }
        try {
            String input = fullCommand.substring(7);
            String[] strings = input.split(" ");
            Integer[] numbers = Arrays.stream(strings)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            return new UnmarkCommand(numbers);
        } catch (NumberFormatException e) {
            throw new NyanDukeException(Ui.ERROR_UNMARK);
        }
    }

    /**
     * Parses a Mark command specified to NyanDuke.
     *
     * @param fullCommand The full command specified to NyanDuke.
     * @return A MarkCommand object representing the user command.
     * @throws NyanDukeException when the user command is invalid.
     */
    private static MarkCommand parseMark(String fullCommand) throws NyanDukeException {
        if (fullCommand.length() == 4) {
            throw new NyanDukeException(Ui.ERROR_MARK);
        }
        try {
            String input = fullCommand.substring(5);
            String[] strings = input.split(" ");
            Integer[] numbers = Arrays.stream(strings)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            return new MarkCommand(numbers);
        } catch (NumberFormatException e) {
            throw new NyanDukeException(Ui.ERROR_MARK);
        }
    }
}
