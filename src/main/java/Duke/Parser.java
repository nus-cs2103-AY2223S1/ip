package Duke;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidCommandException;
import Duke.exceptions.InvalidFindException;
import Duke.exceptions.InvalidIndexException;
import Duke.exceptions.InvalidSecondaryCommandException;
import Duke.exceptions.InvalidTaskNameException;

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
        int maxParameters = 2;
        String delimiter = " ";

        String[] commandWithInfo = input.split(delimiter, maxParameters);
        String commandString = commandWithInfo[0];

        Command command = Command.getCommandFromValue(commandString);
        if (command == null) {
            throw new InvalidCommandException(commandString);
        }
        result.command = command;

        boolean hasInsufficientInformation = commandWithInfo.length != maxParameters;

        switch (command) {
        case LIST:
        case BYE:
            break;
        case CHECK:
        case UNCHECK:
        case DELETE:
            if (hasInsufficientInformation) {
                throw new InvalidIndexException("Please enter an index");
            }
            result.mainData = commandWithInfo[1];
            break;
        case FIND:
            if (hasInsufficientInformation) {
                throw new InvalidFindException();
            }
            result.mainData = commandWithInfo[1];
            break;
        case TODO:
            if (hasInsufficientInformation) {
                throw new InvalidTaskNameException();
            }
            result.mainData = commandWithInfo[1];
            if (result.mainData.isEmpty()) {
                throw new InvalidTaskNameException();
            }
            break;
        case EVENT:
            if (hasInsufficientInformation) {
                throw new InvalidTaskNameException();
            }

            String info = commandWithInfo[1];
            SecondaryCommand at = SecondaryCommand.AT;

            boolean hasNoAtCommandInString = !info.contains(SecondaryCommand.AT.getValue() + ' ');
            if (hasNoAtCommandInString) {
                throw new InvalidSecondaryCommandException(at.getValue());
            }

            int atIndex = info.lastIndexOf(at.getValue());
            boolean isAtCommandAtStartOfString = atIndex == 0;
            if (isAtCommandAtStartOfString) {
                throw new InvalidTaskNameException();
            }

            result.mainData = info.substring(0, atIndex);
            result.secondaryData = info.substring(atIndex + 1 + at.getLength());

            if (result.secondaryData.isEmpty()) {
                throw new InvalidSecondaryCommandException(at.getValue());
            }
            break;
        case DEADLINE:
            if (hasInsufficientInformation) {
                throw new InvalidTaskNameException();
            }

            info = commandWithInfo[1];
            SecondaryCommand by = SecondaryCommand.BY;

            boolean hasNoByCommandInString = !info.contains(SecondaryCommand.BY.getValue() + ' ');
            if (hasNoByCommandInString) {
                throw new InvalidSecondaryCommandException(by.getValue());
            }

            int byIndex = info.lastIndexOf(by.getValue());
            boolean isByCommandAtStartOfString = byIndex == 0;
            if (isByCommandAtStartOfString) {
                throw new InvalidTaskNameException();
            }

            result.mainData = info.substring(0, byIndex);
            result.secondaryData = info.substring(byIndex + 1 + by.getLength());

            if (result.secondaryData.isEmpty()) {
                throw new InvalidSecondaryCommandException(by.getValue());
            }
            break;
        default:
            throw new InvalidCommandException("Command not implemented :(");
        }
        result.mainData = result.mainData.trim();
        result.secondaryData = result.secondaryData.trim();
        return result;
    }
}
