package utils;

import enums.Command;
import enums.SecondaryCommand;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.InvalidSecondaryCommandException;
import exceptions.InvalidTaskNameException;

public class Input {
    private Command command;
    private String mainData;
    private String secondaryData;

    private Input() {
        this.mainData = "";
        this.secondaryData = "";
    }

    public Command getCommand() {
        return command;
    }

    public String getMainData() {
        return mainData;
    }

    public String getSecondaryData() {
        return secondaryData;
    }

    public static Input formatInput(String input) throws DukeException {
        Input result = new Input();
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
                int atIndex = info.lastIndexOf(at.getValue());

                if (atIndex == -1) {
                    throw new InvalidSecondaryCommandException(at.getValue());
                }
                if (atIndex == 0) {
                    throw new InvalidTaskNameException();
                }

                result.mainData = info.substring(0, atIndex - 1);

                if (atIndex + at.getLength() == info.length()) {
                    throw new InvalidSecondaryCommandException(at.getValue());
                }

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
                int byIndex = info.lastIndexOf(by.getValue());

                if (byIndex == -1) {
                    throw new InvalidSecondaryCommandException(by.getValue());
                }
                if (byIndex == 0) {
                    throw new InvalidTaskNameException();
                }

                result.mainData = info.substring(0, byIndex - 1);

                if (byIndex + by.getLength() == info.length()) {
                    throw new InvalidSecondaryCommandException(by.getValue());
                }

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
