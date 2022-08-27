package blob.parser;

import blob.commands.*;
import blob.common.Messages;
import blob.exception.UnknownCommandException;

public class Parser {

    /**
     * Returns a Command after parsing the user input
     *
     * @param userInput the user input to parse
     * @return the Command object corresponding to the parsed input
     */
    public Command parseUserInput(String userInput) throws UnknownCommandException {
        String[] deconstructedInput = userInput.trim().split("\\s+", 2);
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(deconstructedInput[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownCommandException();
        }

        switch (commandType) {

        case TODO:
            return parseTodo(deconstructedInput);

        case DEADLINE:
            return parseDeadline(deconstructedInput);

        case EVENT:
            return parseEvent(deconstructedInput);

        case LIST:
            return new ListCommand();

        case DELETE:
            return parseDelete(deconstructedInput);

        case MARK:
            return parseMark(deconstructedInput);

        case UNMARK:
            return parseUnmark(deconstructedInput);

        case BYE:
            return new ByeCommand();

        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Returns a Command based on parsing user input in the context of a TodoCommand.
     *
     * @param deconstructedInput The deconstructed user input.
     * @return A Command based on parsing user input in the context of a TodoCommand
     */
    private Command parseTodo(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_DESCRIPTION,
                    Messages.MESSAGE_USAGE_TASK_COMMAND);
        }
        String taskDetails = deconstructedInput[1];
        return new TodoCommand(taskDetails);
    }

    /**
     * Returns a Command based on parsing user input in the context of a DeadlineCommand.
     *
     * @param deconstructedInput The deconstructed user input.
     * @return A Command based on parsing user input in the context of a DeadlineCommand
     */
    private Command parseDeadline(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_DESCRIPTION,
                    Messages.MESSAGE_USAGE_TASK_COMMAND);
        }
        String taskDetails = deconstructedInput[1];
        String[] deconstructedDetails = taskDetails.split("\\s+(/by)\\s+", 2);
        if (deconstructedDetails.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_INVALID_DEADLINE,
                    Messages.MESSAGE_USAGE_DEADLINE_COMMAND);
        }
        return new DeadlineCommand(deconstructedDetails[0], deconstructedDetails[1]);
    }

    /**
     * Returns an EventCommand based on user input
     *
     * @param deconstructedInput The deconstructed user input.
     * @return An EventCommand based on user input
     */
    private Command parseEvent(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_DESCRIPTION,
                    Messages.MESSAGE_USAGE_TASK_COMMAND);
        }
        String taskDetails = deconstructedInput[1];
        String[] deconstructedDetails = taskDetails.split("\\s+(/at)\\s+", 2);
        if (deconstructedDetails.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_INVALID_EVENT,
                    Messages.MESSAGE_USAGE_EVENT_COMMAND);
        }

        return new EventCommand(deconstructedDetails[0], deconstructedDetails[1]);
    }

    private Command parseMark(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_INDEX,
                    Messages.MESSAGE_USAGE_MARK_COMMAND);
        }

        try {
            return new MarkCommand(Integer.parseInt(deconstructedInput[1]) - 1);
        } catch (NumberFormatException exception) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }

    private Command parseUnmark(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_INDEX,
                    Messages.MESSAGE_USAGE_MARK_COMMAND);
        }

        try {
            return new UnmarkCommand(Integer.parseInt(deconstructedInput[1]) - 1);
        } catch (NumberFormatException exception) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }

    private Command parseDelete(String[] deconstructedInput) {
        if (deconstructedInput.length < 2) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_MISSING_TASK_INDEX,
                    Messages.MESSAGE_USAGE_DELETE_COMMAND);
        }

        try {
            return new DeleteCommand(Integer.parseInt(deconstructedInput[1]) - 1);
        } catch (NumberFormatException exception) {
            return new InvalidCommand(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }

}
