package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.MarkCommand;
import duke.commands.ReadCommand;
import duke.commands.UnMarkCommand;
import duke.exceptions.DukeUnknownCommandException;


/**
 * Represents a <code>Parser</code> that parse input into command to
 * be executed.
 */
public class Parser {

    /**
     * Parse input to return command for to be executed.
     *
     * @param fullCommand input taken from Ui.
     * @return command to be executed.
     */
    public static Command parse(String fullCommand) throws DukeUnknownCommandException  {

            if (fullCommand.equals("bye")) {
                return new ExitCommand(fullCommand);
            }

            if (fullCommand.equals("list")) {
                return new ReadCommand(fullCommand);
            }

            if (fullCommand.startsWith("mark")) {
                return new MarkCommand(fullCommand);
            }

            if (fullCommand.startsWith("unmark")) {
                return new UnMarkCommand(fullCommand);
            }

            if (fullCommand.startsWith("delete")) {
                return new DeleteCommand(fullCommand);
            }

            if (fullCommand.startsWith("deadline")) {
                return new AddDeadlineCommand(fullCommand);
            }

            if (fullCommand.startsWith("event")) {
                return new AddEventCommand(fullCommand);
            }

            if (fullCommand.startsWith("todo")) {
                return new AddToDoCommand(fullCommand);
            }

            if (fullCommand.startsWith("find")) {
                return new FindCommand(fullCommand);
            }

            throw new DukeUnknownCommandException();


    }
}
