package duke;

import duke.commands.*;

/**
 * Handles interpretation of user inputs.
 */
public class Parser {
    /**
     * Parser constructor.
     */
    public Parser() {
    }

    /**
     * Parse user input into commands.
     *
     * @param fullCommand user inputs.
     * @return Command of task to execute.
     */
    public static Command parse(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ", 2);
        String remainingCommand = "";
        if (splitCommand.length > 1) {
            remainingCommand = splitCommand[1];
        }
        switch(splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
            return new ToDoCommand(remainingCommand);
        case "deadline":
            return new DeadlineCommand(remainingCommand);
        case "event":
            return new EventCommand(remainingCommand);
        case "delete":
            return new DeleteCommand(remainingCommand);
        case "mark":
            return new MarkCommand(remainingCommand);
        case "unmark":
            return new UnmarkCommand(remainingCommand);
        case "list":
            return new DisplayListCommand();
        case "find":
            return new FindCommand(remainingCommand);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
