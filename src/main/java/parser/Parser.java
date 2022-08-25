package parser;

import commands.*;
import exception.UnknownCommandException;

public class Parser {

    /**
     * Returns a Command after parsing the user input
     *
     * @param userInput the user input to parse
     * @return the Command object corresponding to the parsed input
     */
    public Command parseUserInput(String userInput) throws UnknownCommandException {
        String[] deconstructedInput = userInput.trim().split("\\s+", 2);
        CommandType commandType = CommandType.valueOf(deconstructedInput[0]);

        switch (commandType) {

        case TODO:
            return new TodoCommand();

        case DEADLINE:
            return new DeadlineCommand();

        case EVENT:
            return new EventCommand();

        case LIST:
            return new ListCommand();

        case DELETE:
            return new DeleteCommand();

        case MARK:
            return new MarkCommand();

        case UNMARK:
            return new UnmarkCommand();

        case BYE:
            return new ByeCommand();

        default:
            throw new UnknownCommandException();
        }
    }
}
