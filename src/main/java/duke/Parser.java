package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/**
 * Represents a <code>Parser</code> that parses input into <code>Command</code> to be executed.
 */
public class Parser {

    /**
     * Parses input into a <code>Command</code>.
     *
     * @param fullCommand User's input
     * @return <code>Command</code> to be executed.
     * @throws DukeException If input is not of a recognizable pattern.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand(fullCommand);
        }
        if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand(fullCommand);
        }
        if (fullCommand.matches("^todo.*")) {
            return new AddToDoCommand(fullCommand);
        }
        if (fullCommand.matches("^deadline.*")) {
            return new AddDeadlineCommand(fullCommand);
        }
        if (fullCommand.matches("^event.*")) {
            return new AddEventCommand(fullCommand);
        }
        if (fullCommand.matches("^mark [0-9]*$")) {
            return new MarkCommand(fullCommand);
        }
        if (fullCommand.matches("^unmark [0-9]*$")) {
            return new UnmarkCommand(fullCommand);
        }
        if (fullCommand.matches("^delete [0-9]*$")) {
            return new DeleteCommand(fullCommand);
        }
        if (fullCommand.matches("^find.*")) {
            return new FindCommand(fullCommand);
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
    }
}
