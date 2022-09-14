package duke.util;

import duke.DukeException;
import duke.task.*;
import duke.util.command.*;

/**
 * A class that handles user inputs
 */
public class Parser {
    private static final String LINE = "\n----------------------------------------------------------------\n";

    public static Command parseCommand(String command) throws DukeException {
        String input = command.split(" ")[0];

        switch (input) {
            case "bye":
                return new CommandBye(command);
            case "list":
                return new CommandList(command);
            case "mark":
                return new CommandMark(command);
            case "unmark":
                return new CommandUnmark(command);
            case "delete":
                return new CommandDelete(command);
            case "find":
                return new CommandFind(command);
            case "todo":
                return new CommandTodo(command);
            case "deadline":
                return new CommandDeadline(command);
            case "event":
                return new CommandEvent(command);
            default:
                throw new DukeException("Eh what talking you??");
        }

    }
}
