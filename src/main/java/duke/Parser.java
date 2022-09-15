package duke;

import duke.enums.Command;
import duke.enums.SecondaryCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFindException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidSecondaryCommandException;
import duke.exceptions.InvalidTaskNameException;

/**
 * The {@code Parser} class formats a string input into an input object.
 */
public class Parser {

    private Command command;
    private String mainData;
    private String secondaryData;

    /**
     * Constructor for a parser object.
     */
    private Parser() {
        mainData = "";
        secondaryData = "";
    }

    /**
     * Returns the command of the parser.
     *
     * @return the command of the parser.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns the string representing the main data of the parser.
     *
     * @return the string representing the main data of the parser.
     */
    public String getMainData() {
        return mainData;
    }

    /**
     * Returns the string representing the secondary data of the parser.
     *
     * @return the string representing the secondary data of the parser.
     */
    public String getSecondaryData() {
        return secondaryData;
    }

    /**
     * Returns a parser object that contains the input from the user.
     *
     * @param input a string containing text provided by the user.
     * @return an input object containing important information from the user.
     */
    public static Parser formatInput(String input) throws DukeException {
        Parser result = new Parser();
        String[] parsedCommand = input.split(" ", 2);
        result.command = getCommandFromString(parsedCommand[0]);
        input = parsedCommand.length == 2 ? parsedCommand[1] : "";
        handleCommand(result.command, input, result);
        trimResult(result);
        return result;
    }

    /**
     * Handles the command provided depending on the type of command being executed.
     *
     * @param command a command specifying the command to be executed.
     * @param input   a string containing the information associated with the command.
     * @param result  the result that information will be stored in.
     */
    private static void handleCommand(Command command, String input, Parser result) throws DukeException {
        switch (command) {
        case LIST:
        case BYE:
        case HELP:
            break;
        case CHECK:
        case UNCHECK:
        case DELETE:
            handleDeleteCommand(result, input);
            break;
        case FIND:
            handleFindCommand(result, input);
            break;
        case TODO:
            handleTodoCommand(result, input);
            break;
        case EVENT:
            handleEventCommand(result, input);
            break;
        case DEADLINE:
            handleDeadlineCommand(result, input);
            break;
        default:
            handleCommandNotImplemented(command);
        }
    }

    /**
     * Handles the command provided depending on the type of command being executed.
     *
     * @param commandString a string containing an input.
     * @return the command to be executed based on the input.
     */
    private static Command getCommandFromString(String commandString) throws InvalidCommandException {
        Command command = Command.getCommandFromValue(commandString);
        if (command == null) {
            throw new InvalidCommandException(commandString);
        }
        return command;
    }

    /**
     * Handles the delete command with the input provided.
     *
     * @param result the result that information will be stored in.
     * @param input  a string containing the information associated with the command.
     */
    private static void handleDeleteCommand(Parser result, String input) throws InvalidIndexException {
        boolean hasInsufficientInformation = input.isEmpty();
        if (hasInsufficientInformation) {
            throw new InvalidIndexException("Please enter an index");
        }
        result.mainData = input;
    }

    /**
     * Handles the find command with the input provided.
     *
     * @param result the result that information will be stored in.
     * @param input  a string containing the information associated with the command.
     */
    private static void handleFindCommand(Parser result, String input) throws InvalidFindException {
        boolean hasInsufficientInformation = input.isEmpty();
        if (hasInsufficientInformation) {
            throw new InvalidFindException();
        }
        result.mainData = input;
    }

    /**
     * Handles the todos command with the input provided.
     *
     * @param result the result that information will be stored in.
     * @param input  a string containing the information associated with the command.
     */
    private static void handleTodoCommand(Parser result, String input) throws InvalidTaskNameException {
        boolean hasInsufficientInformation = input.isEmpty();
        if (hasInsufficientInformation) {
            throw new InvalidTaskNameException();
        }
        result.mainData = input;
    }

    /**
     * Handles the event command with the input provided.
     *
     * @param result the result that information will be stored in.
     * @param input  a string containing the information associated with the command.
     */
    private static void handleEventCommand(Parser result, String input)
            throws InvalidTaskNameException, InvalidSecondaryCommandException {
        boolean hasInsufficientInformation = input.isEmpty();
        if (hasInsufficientInformation) {
            throw new InvalidTaskNameException();
        }

        SecondaryCommand at = SecondaryCommand.AT;
        boolean hasNoAtCommandInString = !input.contains(at.getValue());
        if (hasNoAtCommandInString) {
            throw new InvalidSecondaryCommandException(at.getValue());
        }

        int atIndex = input.lastIndexOf(at.getValue());
        boolean isAtCommandAtStartOfString = atIndex == 0;
        if (isAtCommandAtStartOfString) {
            throw new InvalidTaskNameException();
        }

        result.secondaryData = input.substring(atIndex + at.getLength());
        if (result.secondaryData.isEmpty()) {
            throw new InvalidSecondaryCommandException(at.getValue());
        }

        result.mainData = input.substring(0, atIndex);
    }

    /**
     * Handles the deadline command with the input provided.
     *
     * @param result the result that information will be stored in.
     * @param input  a string containing the information associated with the command.
     */
    private static void handleDeadlineCommand(Parser result, String input) throws InvalidTaskNameException, InvalidSecondaryCommandException {
        boolean hasInsufficientInformation = input.isEmpty();
        if (hasInsufficientInformation) {
            throw new InvalidTaskNameException();
        }

        SecondaryCommand by = SecondaryCommand.BY;
        boolean hasNoByCommandInString = !input.contains(by.getValue());
        if (hasNoByCommandInString) {
            throw new InvalidSecondaryCommandException(by.getValue());
        }

        int byIndex = input.lastIndexOf(by.getValue());
        boolean isByCommandAtStartOfString = byIndex == 0;
        if (isByCommandAtStartOfString) {
            throw new InvalidTaskNameException();
        }

        result.secondaryData = input.substring(byIndex + by.getLength());
        if (result.secondaryData.isEmpty()) {
            throw new InvalidSecondaryCommandException(by.getValue());
        }

        result.mainData = input.substring(0, byIndex);
    }

    /**
     * Handles the case where the command exist but behaviour have not been implemented.
     *
     * @param command the command that is not implemented
     */
    private static void handleCommandNotImplemented(Command command) throws InvalidCommandException {
        throw new InvalidCommandException(String.format("Command have not been implemented :( Currently %s,",
                command.getValue()));
    }

    /**
     * Trims the information in the main data and secondary data.
     *
     * @param result the result that information is currently stored in.
     */
    private static void trimResult(Parser result) {
        result.mainData = result.mainData.trim();
        result.secondaryData = result.secondaryData.trim();
    }
}
