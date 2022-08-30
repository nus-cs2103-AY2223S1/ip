package duke.parser;

import duke.command.OtherCommand;
import duke.command.MarkingCommand;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.dukeexception.DukeException;

/**
 * Represents a function of Duke robot, which can produce command corresponding to user input.
 */
public class Parser {
    /**
     * Produce different type of command for execution, corresponding to user input.
     * Throws DukeException when the user input format is not right.
     * @param fullCommand A string of a line from System.in. Can be of any format.
     * @return A certain kind of command waiting for execution.
     * @throws DukeException Throws DukeException with remind message when the input format is wrong.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.split(" ")[0].equals("event")
                || fullCommand.split(" ")[0].equals("deadline")
                || fullCommand.split(" ")[0].equals("todo")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("mark")
                || fullCommand.split(" ")[0].equals("unmark")) {
            return new MarkingCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("Get")
                || fullCommand.split(" ")[0].equals("list")) {
            return new OtherCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("find")) {
            return new OtherCommand(fullCommand);
        } else {
            throw new DukeException("Sorry, I don't know your meanings.");
        }
    }
}
