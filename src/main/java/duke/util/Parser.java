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
    public static Command parse(String fullCommand) throws DukeException, DateTimeException {
        String command = getCommandString(fullCommand);
        String commandArgument = getCommandArgument(fullCommand);
        String commandFormat = getCommandFormat(command);
        try {
            if (command.equals("todo")) {
                if (isEmptyInput(commandArgument)) {
                    throw new InvalidCommandFormatException(commandFormat);
                }
                return new TodoCommand(commandArgument);
            } else if (command.equals("deadline")) {
                String[] argumentSplit = getCommandArgumentSplit(commandArgument, " /by ", 2);
                String description = argumentSplit[0];
                LocalDateTime byDateTime = parseDateTime(argumentSplit[1]);
                return new DeadlineCommand(description, byDateTime);
            } else if (command.equals("event")) {
                String[] argumentSplit = getCommandArgumentSplit(commandArgument, " /at ", 2);
                String description = argumentSplit[0];
                LocalDateTime atDateTime = parseDateTime(argumentSplit[1]);
                return new EventCommand(description, atDateTime);
            } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                if (isEmptyInput(commandArgument) || !isDigit(commandArgument)) {
                    throw new InvalidCommandFormatException(commandFormat);
                }
                int taskIndex = Integer.parseInt(commandArgument);
                return command.equals("mark")
                        ? new MarkCommand(taskIndex)
                        : command.equals("unmark")
                        ? new UnmarkCommand(taskIndex)
                        : new DeleteCommand(taskIndex);
            } else if (command.equals("find")) {
                if (isEmptyInput(commandArgument)) {
                    throw new InvalidCommandFormatException(commandFormat);
                }
                return new FindCommand(commandArgument);
            } else if (command.equals("sort")) {
                return SortCommand.parse(commandArgument);
            } else if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            }
        } catch (DateTimeException dte) {
            throw new DukeException("Datetime must be in this format: "
                    + "<DATE> <TIME>\n  DATE: YYYY-MM-DD\n  TIME(optional): HH:MM");
        }
        throw new UnknownCommandException(command);
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
     * @param expectedLength Expected number of smaller arguments after splitting.
     * @return Array of smaller command arguments.
     * @throws InvalidCommandFormatException If the actual number of command arguments after splitting is not equal to
     *              the expected number.
     */
    private static String[] getCommandArgumentSplit(String commandArgument, String delimiter, int expectedLength)
            throws InvalidCommandFormatException {
        String[] argumentSplit = commandArgument.split(delimiter);
        if (argumentSplit.length != expectedLength) {
            throw new InvalidCommandFormatException(DeadlineCommand.getFormat());
        }
        for (int i = 0; i < expectedLength; i++) {
            argumentSplit[i] = argumentSplit[i].strip();
        }
        return argumentSplit;
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

    /**
     * Returns the expected format of the command.
     *
     * @param commandString Name of the command.
     * @return Expected format of the command.
     * @throws UnknownCommandException If the name of the command does not match with any of the existing known
     *              commands.
     */
    private static String getCommandFormat(String commandString) throws UnknownCommandException {
        switch (commandString) {
        case "todo":
            return TodoCommand.getFormat();
        case "deadline":
            return DeadlineCommand.getFormat();
        case "event":
            return EventCommand.getFormat();
        case "find":
            return FindCommand.getFormat();
        case "mark":
            return MarkCommand.getFormat();
        case "unmark":
            return UnmarkCommand.getFormat();
        case "delete":
            return DeleteCommand.getFormat();
        case "list":
            return ListCommand.getFormat();
        case "bye":
            return ByeCommand.getFormat();
        case "sort":
            return SortCommand.getFormat();
        default:
            throw new UnknownCommandException(commandString);
        }
    }
}
