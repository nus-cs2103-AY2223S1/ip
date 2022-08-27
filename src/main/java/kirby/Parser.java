package kirby;

import kirby.commands.*;
import kirby.exceptions.KirbyInvalidCommandException;

/**
 * Parser class handles reading user input and then translating it to command instances.
 */
public class Parser {
    /**
     * Returns the specific type of Command based on users' input.
     *
     * @param inputString the entire input by the user.
     * @return a Command instance based on what type of Command is entered.
     * @throws KirbyInvalidCommandException if the command entered is undefined.
     */
    public static Command parse(String inputString) throws KirbyInvalidCommandException {
        switch (inputString.split(" ")[0]) {
        case "todo":
            return new ToDoCommand(inputString);

        case "event":
            return new EventCommand(inputString);

        case "deadline":
            return new DeadlineCommand(inputString);

        case "mark":
            return new MarkCommand(inputString);

        case "unmark":
            return new UnmarkCommand(inputString);

        case "delete":
            return new DeleteCommand(inputString);

        case "bye":
            return new ExitCommand();

        case "list":
            return new ShowListCommand();

        case "get":
            return new GetCommand(inputString);

        default:
            throw new KirbyInvalidCommandException();
        }
    }
}
