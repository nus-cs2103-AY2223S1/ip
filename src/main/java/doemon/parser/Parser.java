package doemon.parser;

import doemon.command.AddCommand;
import doemon.command.Command;
import doemon.command.DeleteCommand;
import doemon.command.ExitCommand;
import doemon.command.FindCommand;
import doemon.command.HelpCommand;
import doemon.command.ListCommand;
import doemon.command.MarkCommand;
import doemon.command.UnmarkCommand;
import doemon.exception.EmptyTaskException;
import doemon.exception.InvalidCommandException;
import doemon.exception.InvalidTaskNumberException;
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

        if (isByeCommand(inputString)) {
            return new ExitCommand();
        } else if (isListCommand(inputString)) {
            return new ListCommand();
        } else if (isHelpCommand(inputString)) {
            return new HelpCommand();
        }

        String[] inputArr = inputString.split(" ", 2);
        String argument = inputArr.length > 1 ? inputArr[1] : "";

        // Check for mark/unmark/delete
        String toParseInt = argument.trim();
        if (isMarkCommand(inputArr[0], toParseInt)) {
            int taskIndex = Integer.parseInt(toParseInt) - 1;
            return new MarkCommand(taskIndex);
        } else if (isUnmarkCommand(inputArr[0], toParseInt)) {
            int taskIndex = Integer.parseInt(toParseInt) - 1;
            return new UnmarkCommand(taskIndex);
        } else if (isDeleteCommand(inputArr[0], toParseInt)) {
            int taskIndex = Integer.parseInt(toParseInt) - 1;
            return new DeleteCommand(taskIndex);
        }

        // Check for find command
        if (isFindCommand(inputArr[0])) {
            return new FindCommand(argument);
        }
        // Add task command
        return addTaskCommand(inputArr[0], argument.trim());
    }

    /**
     * Handles add task command logic.
     *
     * @param command Command string indicating type of task.
     * @param description Description of task.
     * @return AddCommand instance with respective task type.
     * @throws EmptyTaskException If task description is empty.
     * @throws MissingArgumentException If a deadline or event is missing a flag argument.
     * @throws InvalidCommandException If the command is not todo, deadline or event.
     */
    private static Command addTaskCommand(String command, String description)
            throws EmptyTaskException, MissingArgumentException, InvalidCommandException {
        // Used for deadline/event case
        String[] details = null;
        if (isTodoCommand(command, description)) {
            return new AddCommand(new Todo(description));
        } else if (isDeadlineCommand(command, description)) {
            details = description.split(" /by ", 1);
            return new AddCommand(new Deadline(details[0], details[1]));
        } else if (isEventCommand(command, description)) {
            details = description.split(" /at ", 1);
            return new AddCommand(new Event(details[0], details[1]));
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks if the input string command is bye.
     *
     * @param command Command string to be checked.
     * @return A boolean indicating if the string is a bye.
     */
    private static boolean isByeCommand(String command) {
        return command.equals("bye") || command.equals("b");
    }

    /**
     * Checks if the input string command is list.
     *
     * @param command Command string to be checked.
     * @return A boolean indicating if the string is a list.
     */
    private static boolean isListCommand(String command) {
        return command.equals("list") || command.equals("l");
    }

    /**
     * Checks if the input string command is help.
     *
     * @param command Command string to be checked.
     * @return A boolean indicating if the string is a help.
     */
    private static boolean isHelpCommand(String command) {
        return command.equals("help") || command.equals("h");
    }

    /**
     * Checks if the input string command is mark.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is a mark.
     * @throws InvalidTaskNumberException If the argument provided is not an integer.
     */
    private static boolean isMarkCommand(String command, String description)
            throws InvalidTaskNumberException {
        boolean isMark = command.equals("mark") || command.equals("m");
        if (isMark && !isInteger(description)) {
            throw new InvalidTaskNumberException("mark");
        }
        return isMark && isInteger(description);
    }

    /**
     * Checks if the input string command is unmark.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is an unmark.
     * @throws InvalidTaskNumberException If the argument provided is not an integer.
     */
    private static boolean isUnmarkCommand(String command, String description)
            throws InvalidTaskNumberException {
        boolean isUnmark = command.equals("unmark") || command.equals("u");
        if (isUnmark && !isInteger(description)) {
            throw new InvalidTaskNumberException("unmark");
        }
        return isUnmark && isInteger(description);
    }

    /**
     * Checks if the input string command is delete.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is a delete.
     * @throws InvalidTaskNumberException If the argument provided is not an integer.
     */
    private static boolean isDeleteCommand(String command, String description)
            throws InvalidTaskNumberException {
        boolean isDelete = command.equals("delete") || command.equals("del");
        if (isDelete && !isInteger(description)) {
            throw new InvalidTaskNumberException("delete");
        }
        return isDelete && isInteger(description);
    }

    /**
     * Checks if the input string command is find.
     *
     * @param command Command string to be checked.
     * @return A boolean indicating if the string is a find.
     */
    private static boolean isFindCommand(String command) {
        return command.equals("find");
    }

    /**
     * Checks if the input string command is todo.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is a todo.
     * @throws EmptyTaskException If the description is empty.
     */
    private static boolean isTodoCommand(String command, String description)
            throws EmptyTaskException {
        boolean isTodo = command.equals("todo") || command.equals("t");
        if (isTodo && description.equals("")) {
            throw new EmptyTaskException("todo");
        }
        return isTodo;
    }

    /**
     * Checks if the input string is deadline.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is a deadline.
     * @throws EmptyTaskException If the description is empty.
     * @throws MissingArgumentException If the /by flag argument is missing.
     */
    private static boolean isDeadlineCommand(String command, String description)
            throws EmptyTaskException, MissingArgumentException {
        boolean isDeadline = command.equals("deadline") || command.equals("d");
        String[] details = description.split("/by", 1);
        if (isDeadline && details[0].trim().equals("")) {
            throw new EmptyTaskException("deadline");
        }
        if (isDeadline && details.length == 1) {
            throw new MissingArgumentException("deadline", "/by");
        }
        return isDeadline;
    }

    /**
     * Checks if the input string is event.
     *
     * @param command Command string to be checked.
     * @param description Description string to be checked.
     * @return A boolean indicating if the string is an event.
     * @throws EmptyTaskException If the description is empty.
     * @throws MissingArgumentException If the /at flag argument is missing.
     */
    private static boolean isEventCommand(String command, String description)
            throws EmptyTaskException, MissingArgumentException {
        boolean isEvent = command.equals("event") || command.equals("e");
        if (isEvent && description.equals("")) {
            throw new EmptyTaskException("event");
        }
        String[] details = description.split("/at", 1);
        if (isEvent && details.length == 1) {
            throw new MissingArgumentException("event", "/at");
        }
        return isEvent;
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
