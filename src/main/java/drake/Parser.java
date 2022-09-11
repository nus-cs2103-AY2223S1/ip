package drake;

import drake.commands.*;

/**
 * Command parser.
 */
public class Parser {

    /**
     * Parses and executes the given input using the given module instances.
     * @param fullInput The input given to the bot.
     * @return The Command parsed from the user input.
     */
    public static Command parse(String fullInput) throws UnknownCommandException,
            IncompatibleCommandException, EmptyDescriptionException {
        int firstSpace = fullInput.indexOf(' ');
        String commandText;
        if (fullInput.matches("list|bye")) {
            commandText = fullInput;
        } else if (firstSpace == -1 && fullInput.matches("deadline|event|todo")) {
            throw new EmptyDescriptionException();
        } else if (firstSpace == -1) {
            throw new UnknownCommandException();
        } else {
            commandText = fullInput.substring(0, firstSpace);
        }
        switch (commandText) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(fullInput);
        case "unmark":
            return new UnmarkCommand(fullInput);
        case "todo":
            return new TodoCommand(fullInput);
        case "deadline":
            return new DeadlineCommand(fullInput);
        case "event":
            return new EventCommand(fullInput);
        case "delete":
            return new DeleteCommand(fullInput);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(fullInput);
        default:
            throw new UnknownCommandException();
        }
    }
}
