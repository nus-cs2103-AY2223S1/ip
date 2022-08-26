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
import duke.commands.UnMarkCommand;
import duke.exceptions.DukeException;

public class Parser {

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
            return new UnMarkCommand(fullCommand);
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
