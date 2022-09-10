package drake;

import drake.commands.*;

public class Parser {

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
