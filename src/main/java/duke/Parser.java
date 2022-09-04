package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.OnCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The Parser class deals with making sense of user commands to Duke.
 */
public class Parser {
    private enum Keyword {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, ON, FIND
    }

    /**
     * Executes the user command specified to Duke.
     *
     * @param fullCommand The command specified to Duke.
     * @return A Command object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
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
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a Find command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return A FindCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static FindCommand parseFind(String fullCommand) throws DukeException {
        try {
            return new FindCommand(fullCommand.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Include the keyword you want to find.");
        }
    }

    /**
     * Parses an On command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return An OnCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static OnCommand parseOn(String fullCommand) throws DukeException {
        if (fullCommand.length() == 2) {
            throw new DukeException("Specify the date to check with yyyy-MM-dd.");
        }
        try {
            LocalDate date = LocalDate.parse(fullCommand.substring(3));
            return new OnCommand(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Specify the date to check with yyyy-MM-dd.");
        }
    }

    /**
     * Parses a Delete command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return A DeleteCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static DeleteCommand parseDelete(String fullCommand) throws DukeException {
        if (fullCommand.length() == 6) {
            throw new DukeException("Specify which task to delete with a single integer.");
        }
        try {
            String input = fullCommand.substring(7);
            int n = Integer.parseInt(input);
            return new DeleteCommand(n);
        } catch (NumberFormatException e) {
            throw new DukeException("Specify which task to delete with a single integer.");
        }
    }

    /**
     * Parses an Event command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return An AddCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static AddCommand parseEvent(String fullCommand) throws DukeException {
        try {
            String input = fullCommand.substring(6);
            if (input.startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] splitInput = input.split(" /at ");
            return new AddCommand(new Event(splitInput[0], splitInput[1]));
        } catch (StringIndexOutOfBoundsException | DukeException e) {
            throw new DukeException("The description of an event cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Use /at to provide when an event occurs.");
        }
    }

    /**
     * Parses a Deadline command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return An AddCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static AddCommand parseDeadline(String fullCommand) throws DukeException {
        try {
            String input = fullCommand.substring(9);
            if (input.startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] splitInput = input.split(" /by ");
            return new AddCommand(new Deadline(splitInput[0], splitInput[1]));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Use /by to provide when a deadline must be completed.");
        }
    }

    /**
     * Parses a Todo command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return An AddCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static AddCommand parseTodo(String fullCommand) throws DukeException {
        try {
            return new AddCommand(new Todo(fullCommand.substring(5)));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Parses an Unmark command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return An UnmarkCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static UnmarkCommand parseUnmark(String fullCommand) throws DukeException {
        if (fullCommand.length() == 6) {
            throw new DukeException("Specify which task to unmark with a single integer.");
        }
        try {
            String input = fullCommand.substring(7);
            int n = Integer.parseInt(input);
            return new UnmarkCommand(n);
        } catch (NumberFormatException e) {
            throw new DukeException("Specify which task to unmark with a single integer.");
        }
    }

    /**
     * Parses a Mark command specified to Duke.
     *
     * @param fullCommand The full command specified to Duke.
     * @return A MarkCommand object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    private static MarkCommand parseMark(String fullCommand) throws DukeException {
        if (fullCommand.length() == 4) {
            throw new DukeException("Specify which task to mark with a single integer.");
        }
        try {
            String input = fullCommand.substring(5);
            int n = Integer.parseInt(input);
            return new MarkCommand(n);
        } catch (NumberFormatException e) {
            throw new DukeException("Specify which task to mark with a single integer.");
        }
    }
}
