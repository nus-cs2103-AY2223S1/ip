package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.command.WelcomeCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Class for parsing input string into commands to execute.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Parser {
    private static final String PARSE_FAIL_MESSAGE =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Returns a command based on the specified user input.
     *
     * @param userInput Represents the user input into the system.
     * @return A command based on the user input.
     * @throws DukeException If user input is an invalid string command.
     */
    public static Command parseInput(String userInput) throws DukeException {
        String[] split = userInput.trim().split(" ");
        if (split.length == 0) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        String command = split[0];
        String argument = String.join(" ", Arrays.copyOfRange(split, 1, split.length)).trim();
        switch (command) {
        case "welcome":
            return parseWelcomeCommand(argument);
        case "bye":
            return parseByeCommand(argument);
        case "list":
            return parseListCommand(argument);
        case "mark":
            return parseMarkCommand(argument);
        case "unmark":
            return parseUnmarkCommand(argument);
        case "delete":
            return parseDeleteCommand(argument);
        case "find":
            return parseFindCommand(argument);
        case "todo":
            return parseTodoCommand(argument);
        case "deadline":
            return parseDeadlineCommand(argument);
        case "event":
            return parseEventCommand(argument);
        default:
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
    }

    private static Command parseWelcomeCommand(String argument) throws DukeException {
        if (!argument.isEmpty()) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new WelcomeCommand();
    }

    private static Command parseByeCommand(String argument) throws DukeException {
        if (!argument.isEmpty()) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new ByeCommand();
    }

    private static Command parseListCommand(String argument) throws DukeException {
        if (!argument.isEmpty()) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new ListCommand();
    }

    private static Command parseMarkCommand(String argument) throws DukeException {
        if (!isNumeric(argument)) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new MarkCommand(Integer.parseInt(argument));
    }

    private static Command parseUnmarkCommand(String argument) throws DukeException {
        if (!isNumeric(argument)) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new UnmarkCommand(Integer.parseInt(argument));
    }

    private static Command parseDeleteCommand(String argument) throws DukeException {
        if (!isNumeric(argument)) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }
        return new DeleteCommand(Integer.parseInt(argument));
    }

    private static Command parseFindCommand(String argument) throws DukeException {
        if (argument.isEmpty()) {
            throw new DukeException("OOPS!!! The keyword cannot be empty :-(");
        }
        return new FindCommand(argument);
    }

    private static Command parseTodoCommand(String argument) throws DukeException {
        String description = argument;
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
        }
        return new ToDoCommand(description);
    }

    private static Command parseDeadlineCommand(String argument) throws DukeException {
        String keyword = "/by";
        if (!argument.contains(keyword)) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }

        String description = argument.substring(0, argument.indexOf(keyword)).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty :-(");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(argument.substring(argument.indexOf(keyword) + keyword.length()).trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please format date in YYYY-MM-DD.");
        }
        return new DeadlineCommand(description, date);
    }

    private static Command parseEventCommand(String argument) throws DukeException {
        String keyword = "/at";
        if (!argument.contains(keyword)) {
            throw new DukeException(PARSE_FAIL_MESSAGE);
        }

        String description = argument.substring(0, argument.indexOf(keyword)).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty :-(");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(argument.substring(argument.indexOf(keyword) + keyword.length()).trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please format date in YYYY-MM-DD.");
        }
        return new EventCommand(description, date);
    }

    private static boolean isNumeric(String input) {
        assert input != null;
        
        if (input.isEmpty()) {
            return false;
        }
        
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }
}
