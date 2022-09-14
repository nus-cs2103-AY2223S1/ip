package doemon.parser;

import doemon.command.AddCommand;
import doemon.command.Command;
import doemon.command.DeleteCommand;
import doemon.command.ExitCommand;
import doemon.command.FindCommand;
import doemon.command.ListCommand;
import doemon.command.MarkCommand;
import doemon.command.UnmarkCommand;
import doemon.exception.EmptyTaskException;
import doemon.exception.InvalidCommandException;
import doemon.exception.MissingArgumentException;
import doemon.task.Deadline;
import doemon.task.Event;
import doemon.task.Todo;

/**
 * Handles parsing of user-input.
 */
public class Parser {
    /**
     * Parses the user-input string and returns the related command.
     *
     * @param inputString User-inputted string.
     * @return Command to be executed.
     * @throws EmptyTaskException If task description is empty.
     * @throws InvalidCommandException If the add task command given is invalid.
     * @throws MissingArgumentException If there is a missing argument from a deadline or event.
     */
    public static Command parse(String inputString) throws EmptyTaskException, InvalidCommandException,
            MissingArgumentException {

        switch (inputString) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            break;
        }

        String[] inputArr = inputString.split(" ");

        // Check for mark/unmark/delete
        if (inputArr.length == 2
                && (inputArr[0].equals("mark")
                || inputArr[0].equals("unmark")
                || inputArr[0].equals("delete"))
                && isInteger(inputArr[1])) {
            int taskIndex = Integer.parseInt(inputArr[1]) - 1;
            if (inputArr[0].equals("mark")) {
                return new MarkCommand(taskIndex);
            } else if (inputArr[0].equals("unmark")) {
                return new UnmarkCommand(taskIndex);
            } else {
                return new DeleteCommand(taskIndex);
            }
        }

        // Add new task
        // Used for todo case
        String detail = null;
        // Used for deadline/event case
        String[] details = null;
        switch (inputArr[0]) {
        case "find":
            String keyword = inputString.substring(5);
            return new FindCommand(keyword);
        case "todo":
            detail = inputString.substring(4).trim();
            if (detail.trim().equals("")) {
                throw new EmptyTaskException("todo");
            }
            return new AddCommand(new Todo(detail));
        case "deadline":
            details = inputString.substring(8).trim().split(" /by ");
            if (details[0].trim().equals("")) {
                throw new EmptyTaskException("deadline");
            }
            if (details.length == 1) {
                throw new MissingArgumentException("deadline", "/by");
            }
            return new AddCommand(new Deadline(details[0], details[1]));
        case "event":
            details = inputString.substring(5).trim().split(" /at ");
            if (details[0].trim().equals("")) {
                throw new EmptyTaskException("event");
            }
            if (details.length == 1) {
                throw new MissingArgumentException("event", "/at");
            }
            return new AddCommand(new Event(details[0], details[1]));
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks if the input string after mark or unmark is an integer.
     *
     * @param text String input to be checked.
     * @return A boolean indicating if the text can be parsed into an integer.
     */
    private static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
