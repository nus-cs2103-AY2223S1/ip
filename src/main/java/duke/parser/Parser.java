package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.data.exception.DukeException;

/**
 * This class encapsulates the Parser which checks for
 * the validity of inputs provided
 */
public class Parser {
    /**
     * Makes sense of the commands provided by the user
     * @param input The input provided by the user
     * @return A Command to be executed
     * @throws DukeException If the input is invalid
     */
    public static Command parse(String input) throws DukeException {
        String[] components = input.split(" ", 2);
        String command = components[0];
        String args = components.length == 1 ? "" : components[1];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return prepareList(args);
        case "mark":
            return prepareMark(args);
        case "unmark":
            return prepareUnmark(args);
        case "todo":
            return prepareTodo(args);
        case "deadline":
            return prepareDeadline(args);
        case "event":
            return prepareEvent(args);
        case "delete":
            return prepareDelete(args);
        case "find":
            return prepareFind(args);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Checks if the input is valid
     * @param input An array consisting of the command and task
     * @throws DukeException If the array has a length of less than two
     */
    public static void checkInput(String[] input) throws DukeException {
        if (input.length <= 1) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Checks if the date provided is valid
     * @param date
     * @throws DukeException
     */
    public static boolean isDateValid(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static ListCommand prepareList(String args) throws DukeException {
        if (args.equals("")) {
            return new ListCommand();
        } else {
            isDateValid(args);
            return new ListCommand(args);
        }
    }

    private static MarkCommand prepareMark(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        try {
            int index = Integer.parseInt(args);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    private static UnmarkCommand prepareUnmark(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        try {
            int index = Integer.parseInt(args);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    private static TodoCommand prepareTodo(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        return new TodoCommand(args);
    }

    private static DeadlineCommand prepareDeadline(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        String[] components = args.split(" /by ", 2);
        if (components.length == 1) {
            throw new DukeException("Invalid input!");
        } else if (!isDateValid(components[1])) {
            throw new DukeException("The date included should follow this format: dd/MM/yyyy");
        }

        return new DeadlineCommand(components[0], components[1]);
    }

    private static EventCommand prepareEvent(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        String[] components = args.split(" /at ", 2);
        if (components.length == 1) {
            throw new DukeException("Invalid input!");
        } else if (!isDateValid(components[1])) {
            throw new DukeException("The date included should follow this format: dd/MM/yyyy");
        }

        return new EventCommand(components[0], components[1]);
    }

    private static DeleteCommand prepareDelete(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        try {
            int index = Integer.parseInt(args);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    private static FindCommand prepareFind(String args) throws DukeException {
        if (args.equals("")) {
            throw new DukeException("Invalid input!");
        }

        return new FindCommand(args.split(" "));
    }
}
