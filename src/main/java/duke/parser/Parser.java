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
    public static final String ERROR_UNKOWN_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_DELETE_NUMBER = "OOPS!!! You need to delete a number.";

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
            if (inputArray.length == 1) {
                command = new ExitCommand();
                return command;
            } else {
                throw new DukeException(Parser.ERROR_UNKOWN_MESSAGE);
            }
        case "list":
            if (inputArray.length == 1) {
                command = new ListCommand();
                return command;
            } else {
                throw new DukeException(Parser.ERROR_UNKOWN_MESSAGE);
            }
        case "mark":
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_INVALID_MARK_NUMBER);
            }
            try {
                command = new MarkCommand(Integer.parseInt((inputArray[1])));
                return command;
            } catch (NumberFormatException e) {
                throw new DukeException(Parser.ERROR_INVALID_MARK_NUMBER);
            }
        case "unmark":
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_INVALID_UNMARK_NUMBER);
            }
            try {
                command = new UnMarkCommand(Integer.parseInt((inputArray[1])));
                return command;
            } catch (NumberFormatException e) {
                throw new DukeException(Parser.ERROR_INVALID_UNMARK_NUMBER);
            }
        case "todo":
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
            }
            command = new AddCommand(new ToDo(secondWord));
            return command;
        case "deadline":
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
                command = new AddCommand(new Deadline(description, LocalDate.parse(by)));
                return command;
            } catch (DateTimeParseException e) {
                throw new DukeException(Parser.ERROR_DATE_FORMAT);
            }
        case "event":
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
                command = new AddCommand(new Event(description, LocalDate.parse(at)));
                return command;
            } catch (DateTimeParseException e) {
                throw new DukeException(Parser.ERROR_DATE_FORMAT);
            }
        case "delete":
            if (secondWord.length() == 0) {
                throw new DukeException(Parser.ERROR_DELETE_NUMBER);
            }
            try {
                command = new DeleteCommand(Integer.parseInt(secondWord));
                return command;
            } catch (NumberFormatException e) {
                throw new DukeException(Parser.ERROR_DELETE_NUMBER);
            }
        case "on":
            try {
                command = new OnDateCommand(LocalDate.parse(secondWord));
                return command;
            } catch (DateTimeParseException exception) {
                throw new DukeException(Parser.ERROR_DATE_FORMAT);
            }
        case "find":
            command = new FindCommand(secondWord);
            return command;
        default:
            throw new DukeException(Parser.ERROR_UNKOWN_MESSAGE);
        }
    }
}
