package Duke;

import Duke.commands.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        int firstSpace = input.indexOf(' ');
        String commandText;
        if (input.matches("list|bye")) {
            commandText = input;
        } else if (firstSpace == -1 && input.matches("deadline|event|todo")) {
            throw new DukeException("Description is empty.");
        } else if (firstSpace == -1) {
            throw new DukeException("Unknown command has been thrown.");
        } else {
            commandText = input.substring(0, firstSpace);
        }
        switch (commandText) {
            case "list":
                return new ListCommands();
            case "mark":
                return new MarkCommands(input);
            case "unmark":
                return new UnmarkCommands(input);
            case "todo":
                return new TodoCommands(input);
            case "deadline":
                return new DeadlineCommands(input);
            case "event":
                return new EventCommands(input);
            case "delete":
                return new DeleteCommands(input);
            case "bye":
                return new ByeCommands();
            case "find":
                return new FindCommands(input);
            default:
                throw new DukeException("Unknown commands has been thrown.");
        }
    }
}
