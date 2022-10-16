package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.SnoozeCommand;
import duke.commands.UnmarkCommand;

/**
 * The <code>Parser</code> class parses any command inputted by the user
 */
public class Parser {

    /**
     * A constructor for the <code>Parser</code> class
     *
     * @param input The command inputted by the user
     * @return A <code>Command</code> to be executed
     * @throws DukeException If an unknown input is passed
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = input.split(" ");

        switch (inputs[0].toUpperCase()) {
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(inputs);
        case "UNMARK":
            return new UnmarkCommand(inputs);
        case "TODO":
            return new AddTodoCommand(inputs);
        case "DEADLINE":
            return new AddDeadlineCommand(inputs);
        case "EVENT":
            return new AddEventCommand(inputs);
        case "DELETE":
            return new DeleteCommand(inputs);
        case "BYE":
            return new ByeCommand();
        case "FIND":
            return new FindCommand(inputs);
        case "SNOOZE":
            return new SnoozeCommand(inputs);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means");
        }
    }
}
