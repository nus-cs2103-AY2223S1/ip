package duke.main;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineTaskCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventTaskCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoTaskCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;

/**
 * A parser that parses user input to the commands
 */
public class Parser {

    /**
     * Returns the correct command based on user input
     *
     * @param text The text received from user
     * @throws DukeException If user types in invalid or incomplete commands
     */
    static Command parse(String text) throws DukeException {
        String command = text.split(" ")[0];
        switch (CommandManager.valueOf(command)) {
        case list:
            return new ListCommand();
        case bye:
            return new ByeCommand();
        case todo:
            return new ToDoTaskCommand(text);
        case deadline:
            return new DeadlineTaskCommand(text);
        case event:
            return new EventTaskCommand(text);
        case mark:
            return new MarkCommand(text);
        case unmark:
            return new UnmarkCommand(text);
        case delete:
            return new DeleteCommand(text);
        case find:
            return new FindCommand(text);
        default:
            throw new DukeException("Unknown command received.");
        }
    }
}
