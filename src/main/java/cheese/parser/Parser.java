package cheese.parser;

import cheese.command.ByeCommand;
import cheese.command.Command;
import cheese.command.DeadlineCommand;
import cheese.command.DeleteCommand;
import cheese.command.FindCommand;
import cheese.command.ListCommand;
import cheese.command.MarkCommand;
import cheese.command.SnoozeCommand;
import cheese.command.TodoCommand;
import cheese.command.UnknownCommand;
import cheese.command.UnmarkCommand;
import cheese.exception.CheeseException;

/**
 * Represents a parser to translate user input into command.
 */
public class Parser {

    /** Offset of task index specified by user and index of corresponding task. */
    public static final int INDEX_OFFSET = -1;

    /**
     * Parses user input into command.
     *
     * @param fullCommand User input.
     * @return Instance of <code>Command</code> that corresponds to user input.
     * @throws CheeseException If given user input is invalid or contains error.
     */
    public static Command parse(String fullCommand) throws CheeseException {
        String[] fullCommandArray = fullCommand.split(" ", 2);
        String command = fullCommandArray[0];
        switch (command) {
        case "bye":
            validateCommandHasNArguments(fullCommandArray, 0);
            return new ByeCommand();
        case "list":
            validateCommandHasNArguments(fullCommandArray, 0);
            return new ListCommand();
        case "find":
            validateCommandHasNArguments(fullCommandArray, 1);
            String findArgument = fullCommandArray[1];
            return new FindCommand(findArgument);
        case "mark":
            validateCommandHasNArguments(fullCommandArray, 1);
            String markArgument = fullCommandArray[1];
            return new MarkCommand(parseArgumentToIndex(markArgument));
        case "unmark":
            validateCommandHasNArguments(fullCommandArray, 1);
            String unmarkArgument = fullCommandArray[1];
            return new UnmarkCommand(parseArgumentToIndex(unmarkArgument));
        case "snooze":
            validateCommandHasNArguments(fullCommandArray, 1);
            String snoozeArgument = fullCommandArray[1];
            return new SnoozeCommand(parseArgumentToIndex(snoozeArgument));
        case "delete":
            validateCommandHasNArguments(fullCommandArray, 1);
            String deleteArgument = fullCommandArray[1];
            return new DeleteCommand(parseArgumentToIndex(deleteArgument));
        case "todo":
            validateCommandHasNArguments(fullCommandArray, 1);
            String todoArgument = fullCommandArray[1];
            return new TodoCommand(todoArgument);
        case "deadline":
            return parseDeadlineCommand(fullCommandArray);
        case "event":
            return parseEventCommand(fullCommandArray);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Validates and parses command to create a new deadline.
     *
     * @param fullCommandArray Array containing user input split by space.
     * @return Deadline command with extracted arguments.
     * @throws CheeseException If command has invalid arguments or command does not contain the
     *                         correct flag.
     */
    private static Command parseDeadlineCommand(String[] fullCommandArray) throws CheeseException {
        validateCommandHasNArguments(fullCommandArray, 1);

        String deadlineArgument = fullCommandArray[1];
        boolean hasNoFlag = !deadlineArgument.contains("by");
        if (hasNoFlag) {
            throw new CheeseException("A deadline requires a /by flag.");
        }

        String[] deadlineArgumentArray = deadlineArgument.split("/by", 2);
        boolean hasNoDescription = deadlineArgumentArray[0].length() == 0;
        boolean hasNoDeadline = deadlineArgumentArray[1].length() == 0;
        if (hasNoDescription || hasNoDeadline) {
            throw new CheeseException("A deadline requires both a description and deadline.");
        }

        String description = deadlineArgumentArray[0].trim();
        String deadline = deadlineArgumentArray[1].trim();
        return new DeadlineCommand(description, deadline);
    }

    /**
     * Validates and parses command to create a new event.
     *
     * @param fullCommandArray Array containing user input split by space.
     * @return Event command with extracted arguments.
     * @throws CheeseException If command has invalid arguments or command does not contain the
     *                         correct flag.
     */
    private static Command parseEventCommand(String[] fullCommandArray) throws CheeseException {
        validateCommandHasNArguments(fullCommandArray, 1);

        String eventArgument = fullCommandArray[1];
        boolean hasNoFlag = !eventArgument.contains("/at");
        if (hasNoFlag) {
            throw new CheeseException("A deadline requires an /at flag.");
        }

        String[] eventArgumentArray = eventArgument.split("/at", 2);
        boolean hasNoDescription = eventArgumentArray[0].length() == 0;
        boolean hasNoEventTime = eventArgumentArray[1].length() == 0;
        if (hasNoDescription || hasNoEventTime) {
            throw new CheeseException("An event requires both a description and event time.");
        }

        String description = eventArgumentArray[0].trim();
        String timeInterval = eventArgumentArray[1].trim();
        return new DeadlineCommand(description, timeInterval);
    }

    /**
     * Checks if given user input has n arguments
     *
     * @param fullCommandArray Array containing user input after splitting by space.
     * @param n                Desired number of arguments.
     * @throws CheeseException If given user input does not contain n arguments.
     */
    private static void validateCommandHasNArguments(String[] fullCommandArray, int n)
            throws CheeseException {
        boolean hasNArguments = fullCommandArray.length == n + 1;
        if (!hasNArguments) {
            throw new CheeseException();
        }
    }

    /**
     * Parses String argument to int.
     *
     * @param argument Argument to parse to int.
     * @return Integer that is parsed.
     * @throws CheeseException If argument is in non-integer format.
     */
    private static int parseArgumentToIndex(String argument) throws CheeseException {
        int index;
        try {
            index = Integer.parseInt(argument) + INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new CheeseException("Cannot convert non-integer to integer.");
        }
        return index;
    }
}
