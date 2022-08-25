package Duke;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidCommandException;
import Duke.exceptions.InvalidSecondaryCommandException;
import Duke.exceptions.InvalidTaskNameException;

/**
 * The {@code Input} class formats a string input into an input object.
 */
public class Parser {

    private Command command;
    private String mainData;
    private String secondaryData;

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

        String[] commandWithInfo = input.split(" ", maxParameters);
        String commandString = commandWithInfo[0];

        Command command = Command.getCommandFromValue(commandString);
        if (command != null) {
            result.command = command;
        } else {
            throw new InvalidCommandException(commandString);
        }

        switch (command) {
        case LIST:
        case BYE:
            break;
        case CHECK:
        case UNCHECK:
        case DELETE:
        case TODO:
            if (commandWithInfo.length != maxParameters) {
                throw new InvalidTaskNameException();
            }
            result.mainData = commandWithInfo[1];
            if (result.mainData.isEmpty()) {
                throw new InvalidTaskNameException();
            }
            break;
        case EVENT:
            if (commandWithInfo.length != maxParameters) {
                throw new InvalidTaskNameException();
            }

            String info = commandWithInfo[1];
            SecondaryCommand at = SecondaryCommand.AT;

            if (!info.contains(SecondaryCommand.AT.getValue() + ' ')) {
                throw new InvalidSecondaryCommandException(at.getValue());
            }

            int atIndex = info.lastIndexOf(at.getValue());
            if (atIndex == 0) {
                throw new InvalidTaskNameException();
            }

            result.mainData = info.substring(0, atIndex - 1);
            result.secondaryData = info.substring(atIndex + 1 + at.getLength());

            if (result.mainData.isEmpty()) {
                throw new InvalidTaskNameException();
            }
            if (result.secondaryData.isEmpty()) {
                throw new InvalidSecondaryCommandException(at.getValue());
            }
            break;
        case DEADLINE:
            if (commandWithInfo.length != maxParameters) {
                throw new InvalidTaskNameException();
            }

            info = commandWithInfo[1];
            SecondaryCommand by = SecondaryCommand.BY;

            if (!info.contains(SecondaryCommand.BY.getValue() + ' ')) {
                throw new InvalidSecondaryCommandException(by.getValue());
            }

            int byIndex = info.lastIndexOf(by.getValue());
            if (byIndex == 0) {
                throw new InvalidTaskNameException();
            }

            result.mainData = info.substring(0, byIndex - 1);
            result.secondaryData = info.substring(byIndex + 1 + by.getLength());

            if (result.mainData.isEmpty()) {
                throw new InvalidTaskNameException();
            }
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
