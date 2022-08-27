package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the provided user input to return the command to be executed.
     *
     * @param input The provided user input.
     * @return The command the user wants to execute.
     * @throws DukeException if the command has invalid arguments.
     */
    public Command parse(String input) throws DukeException {
        int idx = input.indexOf(' ');
        String textCommand = idx < 0 ? input : input.substring(0, idx);

        switch (textCommand) {
        case "bye":
            return new ByeCommand();

        case "todo":
            return parseTodo(input);

        case "deadline":
            return parseDeadline(input);

        case "event":
            return parseEvent(input);

        case "list":
            return new ListCommand();

        case "mark":
            return parseMark(input);

        case "unmark":
            return parseUnmark(input);

        case "delete":
            return parseDelete(input);

        case "find":
            return parseFind(input);

        default:
            return new UnknownCommand();
        }
    }

    private TodoCommand parseTodo(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Invalid todo command. Example: todo borrow book");
        }
        return new TodoCommand(input.substring(5));
    }

    private DeadlineCommand parseDeadline(String input) throws DukeException {
        int idx = input.indexOf("/by");

        if (idx < 0 || idx == 9 || input.length() <= idx + 5) {
            throw new DukeException("Invalid deadline command. Example: deadline return book /by 2022-04-02");
        }

        String description = input.substring(9, idx - 1);

        try {
            LocalDate by = LocalDate.parse(input.substring(idx + 4));
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Example: deadline return book /by 2022-04-02");
        }

    }

    private EventCommand parseEvent(String input) throws DukeException {
        int idx = input.indexOf("/at");

        if (idx < 0 || idx == 6 || input.length() <= idx + 5) {
            throw new DukeException("Invalid deadline command. Example: event project meeting /at 2020-02-29");
        }

        String description = input.substring(6, idx - 1);

        try {
            LocalDate at = LocalDate.parse(input.substring(idx + 4));
            return new EventCommand(description, at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Example: event project meeting /at 2020-02-29");
        }

    }

    private MarkCommand parseMark(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'mark n'");
        }

        try {
            // Get the index of the task to mark as complete
            int idx = Integer.parseInt(components[1]);
            return new MarkCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private UnmarkCommand parseUnmark(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'unmark n'");
        }

        try {
            // Get the index of the task to unmark
            int idx = Integer.parseInt(components[1]);
            return new UnmarkCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private DeleteCommand parseDelete(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'delete n'");
        }

        try {
            // Get the index of the task to delete
            int idx = Integer.parseInt(components[1]);
            return new DeleteCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private FindCommand parseFind(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Usage: find keyword");
        }
        return new FindCommand(input.substring(5));
    }
}
