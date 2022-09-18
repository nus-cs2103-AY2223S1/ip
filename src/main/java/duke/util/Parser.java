package duke.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.enums.SortMetric;
import duke.enums.SortOrder;
import duke.exception.DukeException;
import duke.exception.InvalidCommandFormatException;
import duke.exception.UnknownCommandException;

/**
 * The Parser parses input from the user and returns the corresponding commands to execute.
 *
 * @author njxue
 * @version v0.1
 */
public class Parser {
    /**
     * Parses the full input from the user and returns the correct Command object.
     *
     * @param fullCommand Full input from the user.
     * @return Command object corresponding to the user's input.
     * @throws DukeException If the user provides an empty command, or if the command is not a recognised command.
     * @throws DateTimeException If the string representation of a LocalDateTime cannot be parsed
     *                          correctly due to incorrect formatting.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command = getCommandString(fullCommand);
        String commandArgument = getCommandArgument(fullCommand);
        try {
            switch(command) {
            case "todo":
                return parseTodoCommand(commandArgument);
            case "deadline":
                return parseDeadlineCommand(commandArgument);
            case "event":
                return parseEventCommand(commandArgument);
            case "mark":
                return parseMarkCommand(commandArgument);
            case "unmark":
                return parseUnmarkCommand(commandArgument);
            case "delete":
                return parseDeleteCommand(commandArgument);
            case "find":
                return parseFindCommand(commandArgument);
            case "sort":
                return parseSortCommand(commandArgument);
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            default:
                throw new UnknownCommandException(command);
            }
        } catch (DateTimeException dte) {
            throw new DukeException("Datetime must be in this format: "
                    + "<DATE> <TIME>\n  DATE: YYYY-MM-DD\n  TIME(optional): HH:MM");
        }
    }

    /**
     * Parses a TodoCommand.
     *
     * @param commandArgument Description of the Todo task.
     * @return TodoCommand with the specified description.
     * @throws InvalidCommandFormatException If description of the Todo task is empty.
     */
    private static TodoCommand parseTodoCommand(String commandArgument) throws InvalidCommandFormatException {
        if (isEmptyInput(commandArgument)) {
            throw new InvalidCommandFormatException(TodoCommand.getFormat());
        }
        return new TodoCommand(commandArgument);
    }

    /**
     * Parses a DeadlineCommand.
     *
     * @param commandArgument Command argument specifying the description and deadline of the Deadline Task.
     * @return DeadlineCommand with the specified description and deadline.
     * @throws InvalidCommandFormatException If description of the Deadline Task is empty.
     */
    private static DeadlineCommand parseDeadlineCommand(String commandArgument) throws InvalidCommandFormatException {
        String[] argumentSplit = splitByDelimiter(commandArgument, " /by ");
        if (argumentSplit.length < 2) {
            throw new InvalidCommandFormatException(DeadlineCommand.getFormat());
        }
        String description = argumentSplit[0];
        LocalDateTime byDateTime = parseDateTime(argumentSplit[1]);
        return new DeadlineCommand(description, byDateTime);
    }

    /**
     * Parses an EventCommand.
     *
     * @param commandArgument Command argument specifying the description and datetime of the Event Task.
     * @return EventCommand with the specified description and datetime.
     * @throws InvalidCommandFormatException If description of the Event Task is empty.
     */
    private static EventCommand parseEventCommand(String commandArgument) throws InvalidCommandFormatException {
        String[] argumentSplit = splitByDelimiter(commandArgument, " /at ");
        if (argumentSplit.length < 2) {
            throw new InvalidCommandFormatException(EventCommand.getFormat());
        }
        String description = argumentSplit[0];
        LocalDateTime byDateTime = parseDateTime(argumentSplit[1]);
        return new EventCommand(description, byDateTime);
    }

    /**
     * Parses a MarkCommand.
     *
     * @param commandArgument Command argument specifying the index of the task in the TaskList to mark.
     * @return MarkCommand with the specified task index.
     * @throws InvalidCommandFormatException If commandArgument is not a numeric string.
     */
    private static MarkCommand parseMarkCommand(String commandArgument) throws InvalidCommandFormatException {
        int taskIndex = parseTaskIndex(commandArgument, MarkCommand.getFormat());
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses an UnmarkCommand.
     *
     * @param commandArgument Command argument specifying the index of the task in the TaskList to unmark.
     * @return UnmarkCommand with the specified task index.
     * @throws InvalidCommandFormatException If commandArgument is not a numeric string.
     */
    private static UnmarkCommand parseUnmarkCommand(String commandArgument) throws InvalidCommandFormatException {
        int taskIndex = parseTaskIndex(commandArgument, UnmarkCommand.getFormat());
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses a DeleteCommand.
     *
     * @param commandArgument Command argument specifying the index of the task in the TaskList to delete.
     * @return DeleteCommand with the specified task index.
     * @throws InvalidCommandFormatException If commandArgument is not a numeric string.
     */
    private static DeleteCommand parseDeleteCommand(String commandArgument) throws InvalidCommandFormatException {
        int taskIndex = parseTaskIndex(commandArgument, DeleteCommand.getFormat());
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses a FindCommand.
     *
     * @param commandArgument Search term tasks to find.
     * @return FindCommand with the specified search term.
     * @throws InvalidCommandFormatException If the search term of the FindCommand is empty.
     */
    private static FindCommand parseFindCommand(String commandArgument) throws InvalidCommandFormatException {
        if (isEmptyInput(commandArgument)) {
            throw new InvalidCommandFormatException(FindCommand.getFormat());
        }
        return new FindCommand(commandArgument);
    }

    /**
     * Parses a SortCommand.
     *
     * @param commandArgument Command argument specifying the order and/or metric of the sorting mechanism.
     * @return SortCommand with the specified sort order and/or metric.
     * @throws InvalidCommandFormatException If the specified sort order and/or metric is not recognised.
     */
    private static SortCommand parseSortCommand(String commandArgument) throws InvalidCommandFormatException {
        if (isEmptyInput(commandArgument)) {
            return new SortCommand();
        }

        String[] commandArgSplit = splitByDelimiter(commandArgument, "\\s+");
        int numArguments = commandArgSplit.length;

        if (numArguments > 2) {
            throw new InvalidCommandFormatException(SortCommand.getFormat());
        }

        if (numArguments == 2) {
            SortOrder order = SortOrder.parse(commandArgSplit[0]);
            SortMetric metric = SortMetric.parse(commandArgSplit[1]);
            return new SortCommand(order, metric);
        }

        String arg = commandArgSplit[0];
        if (!SortOrder.isValidOrder(arg) && !SortMetric.isValidMetric(arg)) {
            throw new InvalidCommandFormatException(SortCommand.getFormat());
        }
        return SortOrder.isValidOrder(arg)
                ? new SortCommand(SortOrder.parse(arg))
                : new SortCommand(SortMetric.parse(arg));
    }

    /**
     * Returns a LocalDateTime object from an appropriately-formatted string.
     *
     * @param dateTimeString String representation of a LocalDateTime object. The expected format is
     *                       YYYY-MM-DDTHH:MM or YYYY-MM-DD.
     * @return LocalDateTime object corresponding to the dateTimeString.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        String[] dateTimeSplit = dateTimeString.split("[ T]");
        String isoDateFormat = "";
        if (dateTimeSplit.length == 1) {
            isoDateFormat = String.format("%sT23:59", dateTimeSplit[0]);
        } else {
            isoDateFormat = String.format("%sT%s", dateTimeSplit[0], dateTimeSplit[1]);
        }
        return LocalDateTime.parse(isoDateFormat);
    }

    /**
     * Parses a command argument and returns a task index.
     *
     * @param commandArgument String representation of a task index.
     * @param commandFormat Command format of the command associated with the command argument.
     * @return Task index.
     * @throws InvalidCommandFormatException If command argument is not a digit or is empty.
     */
    private static int parseTaskIndex(String commandArgument, String commandFormat)
            throws InvalidCommandFormatException {
        if (isEmptyInput(commandArgument) || !isDigit(commandArgument)) {
            throw new InvalidCommandFormatException(commandFormat);
        }
        return Integer.parseInt(commandArgument);
    }

    /**
     * Returns the name of the command from the full user input.
     *
     * @param fullCommand Full user input.
     * @return Name of the command corresponding to the user input.
     * @throws DukeException If the command string is empty.
     */
    private static String getCommandString(String fullCommand) throws DukeException {
        String[] fullCommandSplit = fullCommand.split(" ", 2);
        String command = fullCommandSplit[0].strip();
        if (isEmptyInput(command)) {
            throw new DukeException("Command cannot be empty");
        }
        return command;
    }

    /**
     * Returns the command arguments from the full user input.
     *
     * @param fullCommand Full user input.
     * @return Command arguments from the full user input
     */
    private static String getCommandArgument(String fullCommand) {
        String[] fullCommandSplit = fullCommand.split(" ", 2);
        return fullCommandSplit.length > 1 ? fullCommandSplit[1].strip() : "";
    }

    /**
     * Splits the command argument further into smaller arguments and returns them in an array.
     *
     * @param commandArgument Initial command argument to split.
     * @param delimiter Delimiter to split the command argument by.
     * @return Array of smaller command arguments.
     */
    private static String[] splitByDelimiter(String commandArgument, String delimiter) {
        String[] argumentSplit = commandArgument.split(delimiter);
        for (int i = 0; i < argumentSplit.length; i++) {
            argumentSplit[i] = argumentSplit[i].strip();
        }
        return argumentSplit;
    }

    /**
     * Checks if a given string contains only digits.
     *
     * @param string The target string.
     * @return True if str contains only digits. Returns false otherwise.
     */
    private static boolean isDigit(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given string input is empty.
     *
     * @param input The input, which can either be the command string or the command arguments.
     * @return True, if the input is empty.
     */
    private static boolean isEmptyInput(String input) {
        return input.length() == 0;
    }
}
