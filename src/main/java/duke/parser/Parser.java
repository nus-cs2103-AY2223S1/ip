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
import duke.command.OnDateCommand;
import duke.command.UnMarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Deals with making sense of user commands.
 *
 * @author Bryan Ng Zi Hao
 */
public abstract class Parser {
    public static final String ERROR_INVALID_MARK_NUMBER = "OOPS!!! You need to mark a number.";
    public static final String ERROR_INVALID_UNMARK_NUMBER = "OOPS!!! You need to unmark a number.";
    public static final String ERROR_EMPTY_DESCRIPTION = "OOPS!!! The description cannot be empty.";
    public static final String ERROR_EMPTY_DATE = "OOPS!!! The date is missing.";
    public static final String ERROR_DATE_FORMAT = "Invalid date format (yyyy-mm-dd).";
    public static final String ERROR_UNKNOWN_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_DELETE_NUMBER = "OOPS!!! You need to delete a number.";

    /**
     * Executes the command ExitCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command exitCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            Command command = new ExitCommand();
            return command;
        } else {
            throw new DukeException(Parser.ERROR_UNKNOWN_MESSAGE);
        }
    }

    /**
     * Executes the command ListCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command listCommand(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            Command command = new ListCommand();
            return command;
        } else {
            throw new DukeException(Parser.ERROR_UNKNOWN_MESSAGE);
        }
    }

    /**
     * Executes the command MarkCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command markCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        if (secondWord.length() == 0) {
            throw new DukeException(Parser.ERROR_INVALID_MARK_NUMBER);
        }
        try {
            Command command = new MarkCommand(Integer.parseInt((inputArray[1])));
            return command;
        } catch (NumberFormatException e) {
            throw new DukeException(Parser.ERROR_INVALID_MARK_NUMBER);
        }
    }

    /**
     * Executes the command UnMarkCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command unmarkCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        if (secondWord.length() == 0) {
            throw new DukeException(Parser.ERROR_INVALID_UNMARK_NUMBER);
        }
        try {
            Command command = new UnMarkCommand(Integer.parseInt((inputArray[1])));
            return command;
        } catch (NumberFormatException e) {
            throw new DukeException(Parser.ERROR_INVALID_UNMARK_NUMBER);
        }
    }

    /**
     * Executes the command AddCommand for todos.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command todoCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        if (secondWord.length() == 0) {
            throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
        }
        Command command = new AddCommand(new ToDo(secondWord));
        return command;
    }

    /**
     * Executes the command AddCommand for deadlines.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command deadlineCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        try {
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
            }
            String[] dArray = secondWord.split(" /by ", 2);
            if (dArray.length == 1) {
                throw new DukeException(Parser.ERROR_EMPTY_DATE);
            }
            String description = dArray[0];
            String by = dArray[1];
            Command command = new AddCommand(new Deadline(description, LocalDate.parse(by)));
            return command;
        } catch (DateTimeParseException e) {
            throw new DukeException(Parser.ERROR_DATE_FORMAT);
        }
    }

    /**
     * Executes the command AddCommand for events.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command eventCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        try {
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
            }
            String[] eArray = secondWord.split(" /at ", 2);
            if (eArray.length == 1) {
                throw new DukeException(Parser.ERROR_EMPTY_DATE);
            }
            String description = eArray[0];
            String at = eArray[1];
            Command command = new AddCommand(new Event(description, LocalDate.parse(at)));
            return command;
        } catch (DateTimeParseException e) {
            throw new DukeException(Parser.ERROR_DATE_FORMAT);
        }
    }

    /**
     * Executes the command DeleteCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command deleteCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        if (secondWord.length() == 0) {
            throw new DukeException(Parser.ERROR_DELETE_NUMBER);
        }
        try {
            Command command = new DeleteCommand(Integer.parseInt(secondWord));
            return command;
        } catch (NumberFormatException e) {
            throw new DukeException(Parser.ERROR_DELETE_NUMBER);
        }
    }

    /**
     * Executes the command OnDateCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command onCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        try {
            Command command = new OnDateCommand(LocalDate.parse(secondWord));
            return command;
        } catch (DateTimeParseException exception) {
            throw new DukeException(Parser.ERROR_DATE_FORMAT);
        }
    }

    /**
     * Executes the command FindCommand.
     *
     * @param inputArray The input from the user in an array.
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command findCommand(String[] inputArray) throws DukeException {
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        Command command = new FindCommand(secondWord);
        return command;
    }

    /**
     * Parses the users input to check which command should be run.
     *
     * @return The command that is required to run.
     * @throws DukeException There is an error in returning the command to run.
     */
    public static Command parse(String input) throws DukeException {
        Command command;
        String[] inputArray = input.split(" ", 2);
        String firstWord = inputArray[0];
        String secondWord = "";
        if (inputArray.length == 2) {
            secondWord = inputArray[1];
        }
        switch (firstWord) {
        case "bye":
            return exitCommand(inputArray);
        case "list":
            return listCommand(inputArray);
        case "mark":
            return markCommand(inputArray);
        case "unmark":
            return unmarkCommand(inputArray);
        case "todo":
            return todoCommand(inputArray);
        case "deadline":
            return deadlineCommand(inputArray);
        case "event":
            return eventCommand(inputArray);
        case "delete":
            return deleteCommand(inputArray);
        case "on":
            return onCommand(inputArray);
        case "find":
            return findCommand(inputArray);
        default:
            throw new DukeException(Parser.ERROR_UNKNOWN_MESSAGE);
        }
    }
}
