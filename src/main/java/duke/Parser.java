package duke;

import command.*;

/**
 * The class that deals with making sense of the user command in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Parser {

    /**
     * Parses the user input and returns a command corresponding to the user input.
     *
     * @param str The user input.
     * @return The command corresponding to the user input.
     */

    public Command parse(String str) throws DukeException {
        String[] input = str.split(" ");
        switch (input[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(str);
        case "unmark":
            return new UnmarkCommand(str);
        case "todo":
            return new TodoCommand(str);
        case "deadline":
            return new DeadlineCommand(str);
        case "event":
            return new EventCommand(str);
        case "delete":
            return new DeleteCommand(str);
        case "find":
            return new FindCommand(str);
        default:
            return new ErrorCommand("I'm sorry, but I don't know what that means :-(");
        }
    }

}
