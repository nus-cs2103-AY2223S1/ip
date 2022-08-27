package cheese.parser;

import cheese.command.ByeCommand;
import cheese.command.Command;
import cheese.command.DeadlineCommand;
import cheese.command.DeleteCommand;
import cheese.command.ListCommand;
import cheese.command.MarkCommand;
import cheese.command.TodoCommand;
import cheese.command.UnknownCommand;
import cheese.command.UnmarkCommand;
import cheese.exception.CheeseException;

/**
 * Represents a parser to translate user input into command.
 */
public class Parser {
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
        case "mark":
            validateCommandHasNArguments(fullCommandArray, 1);
            String markArgument = fullCommandArray[1];
            return new MarkCommand(parseArgumentToInt(markArgument));
        case "unmark":
            validateCommandHasNArguments(fullCommandArray, 1);
            String unmarkArgument = fullCommandArray[1];
            return new UnmarkCommand(parseArgumentToInt(unmarkArgument));
        case "delete":
            validateCommandHasNArguments(fullCommandArray, 1);
            String deleteArgument = fullCommandArray[1];
            return new DeleteCommand(parseArgumentToInt(deleteArgument));
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
     *         correct flag.
     */
    private static Command parseDeadlineCommand(String[] fullCommandArray) throws CheeseException {
        validateCommandHasNArguments(fullCommandArray, 1);
        String deadlineArgument = fullCommandArray[1];
        if (deadlineArgument.indexOf("/by") == -1) {
            throw new CheeseException("A deadline requires a /by flag.");
        }
        String[] deadlineArgumentArray = deadlineArgument.split("/by", 2);
        if (deadlineArgumentArray[0].length() == 0 || deadlineArgumentArray[1].length() == 0) {
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
     *         correct flag.
     */
    private static Command parseEventCommand(String[] fullCommandArray) throws CheeseException {
        validateCommandHasNArguments(fullCommandArray, 1);
        String eventArgument = fullCommandArray[1];
        if (eventArgument.indexOf("/at") == -1) {
            throw new CheeseException("A deadline requires an /at flag.");
        }
        String[] eventArgumentArray = eventArgument.split("/at", 2);
        if (eventArgumentArray[0].length() == 0 || eventArgumentArray[1].length() == 0) {
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
     * @param n Desired number of arguments.
     * @throws CheeseException If given user input does not contain n arguments.
     */
    private static void validateCommandHasNArguments(String[] fullCommandArray, int n)
            throws CheeseException {
        if (fullCommandArray.length != n + 1) {
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
    private static int parseArgumentToInt(String argument) throws CheeseException {
        int parsedArgument;
        try {
            parsedArgument = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new CheeseException("Cannot convert non-integer to integer.");
        }
        return parsedArgument;
    }
}
