package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Interprets user input and creating the corresponding command.
 *
 * @author Tan Jun Wei
 */
public abstract class Parser {
    private static final String BULK_DELETE_FLAG = "-f";

    /**
     * Checks if input string contains restricted characters.
     * If a character used to encode task is entered by user, an exception is thrown.
     *
     * @param input The user input.
     * @throws DukeException If the user input contains restricted characters.
     */
    private static void detectRestrictedCharacters(String input) throws DukeException {
        String restrictedCharacter = Task.getEncodingSeparator();
        if (input.contains(restrictedCharacter)) {
            throw new DukeException("The character \"||\" is not allowed.");
        }
    }

    private static String[] convertInputToArr(String input) {
        return input.trim().split(" ", 2);
    }

    private static Command parseListCommand() {
        return new ListCommand();
    }

    private static Command parseExitCommand() {
        return new ExitCommand();
    }

    private static Command parseMarkCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("Please specify a task to mark.");
        }
        try {
            return new MarkCommand(Integer.parseInt(commandArgs[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid number.");
        }
    }

    private static Command parseUnmarkCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("Please specify a task to unmark.");
        }
        try {
            return new UnmarkCommand(Integer.parseInt(commandArgs[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid number.");
        }
    }

    private static Command parseDeleteCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("Please specify a task to delete.");
        }

        if (commandArgs[1].contains(BULK_DELETE_FLAG)) {
            String[] deleteArgs = (commandArgs[1].split("-f ", 2));
            String termToDeleteBy = "";
            if (deleteArgs.length == 2) {
                termToDeleteBy = deleteArgs[1];
            }
            return new DeleteCommand(termToDeleteBy);
        }

        try {
            return new DeleteCommand(Integer.parseInt(commandArgs[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a valid number.");
        }
    }

    private static Command parseFindCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("The search term cannot be empty.");
        }
        return new FindCommand(commandArgs[1]);
    }

    private static Command parseTodoCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(commandArgs[1]));
    }

    private static Command parseDeadlineCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] deadlineArgs = commandArgs[1].split(" /by ");
        if (deadlineArgs.length < 2) {
            throw new DukeException("Please provide a description and deadline, "
                    + "separated by \"/by\".");
        }
        try {
            LocalDate date = LocalDate.parse(deadlineArgs[1]);
            return new AddCommand(new Deadline(deadlineArgs[0], date));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a valid date. (YYYY-MM-DD)");
        }
    }

    private static Command parseEventCommand(String[] commandArgs) throws DukeException {
        if (commandArgs.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] eventArgs = commandArgs[1].split(" /at ");
        if (eventArgs.length < 2) {
            throw new DukeException("Please provide a description and date, "
                    + "separated by \"/at\".");
        }
        try {
            LocalDate date = LocalDate.parse(eventArgs[1]);
            return new AddCommand(new Event(eventArgs[0], date));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a valid date. (YYYY-MM-DD)");
        }
    }

    /**
     * Parses the user input and creates the corresponding command.
     *
     * @param input String representing the user's input.
     * @return The command corresponding to the user's input.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        detectRestrictedCharacters(input);
        String[] commandArgs = convertInputToArr(input);
        String commandType = commandArgs[0];
        switch (commandType) {
        case "list":
            return parseListCommand();
        case "bye":
            return parseExitCommand();
        case "mark":
            return parseMarkCommand(commandArgs);
        case "unmark":
            return parseUnmarkCommand(commandArgs);
        case "delete":
            return parseDeleteCommand(commandArgs);
        case "find":
            return parseFindCommand(commandArgs);
        case "todo":
            return parseTodoCommand(commandArgs);
        case "deadline":
            return parseDeadlineCommand(commandArgs);
        case "event":
            return parseEventCommand(commandArgs);
        default:
            throw new DukeException("Please specify a valid command.");
        }
    }
}
