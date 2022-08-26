package duke;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.DukeException;

/**
 * Used to parse user's input and returns the corresponding commands.
 */
public class Parser {
    /**
     * Parses the input command and returns the corresponding command.
     *
     * @param command The specified input command.
     * @return The corresponding command to be executed.
     * @throws DukeException if an error was thrown during the construction of command.
     */
    public static Command parse(String command) throws DukeException {
        // Limit of 2 is used to avoid splitting the second argument in fullCommand.
        String[] inputValues = command.split(" ", 2);
        switch (inputValues[0]) {
        case "bye": {
            return new ByeCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "mark": {
            return new MarkCommand(inputValues);
        }
        case "unmark": {
            return new UnmarkCommand(inputValues);
        }
        case "todo": {
            return new TodoCommand(inputValues);
        }
        case "deadline": {
            return new DeadlineCommand(inputValues);
        }
        case "event": {
            return new EventCommand(inputValues);
        }
        case "delete": {
            return new DeleteCommand(inputValues);
        }
        case "find": {
            return new FindCommand(inputValues);
        }
        default: {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        }
    }
}
