package duke.util;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

/**
 * Class that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parse the user input.
     *
     * @param input the input
     * @return the command to be executed
     * @throws DukeException the duke exception
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] segments = input.split(" ", 2);
        try {
            switch (segments[0]) {
            case "list":
                return new ListCommand();
            //Fallthrough

            case "mark":
                return new MarkCommand(segments[1]);
            //Fallthrough

            case "unmark":
                return new UnmarkCommand(segments[1]);
            //Fallthrough

            case "todo":
                return new TodoCommand(segments[1]);
            //Fallthrough

            case "event":
                return new EventCommand(segments[1]);
            //Fallthrough

            case "deadline":
                return new DeadlineCommand(segments[1]);
            //Fallthrough

            case "delete":
                return new DeleteCommand(segments[1]);
            //Fallthrough

            case "find":
                return new FindCommand(segments[1]);

            case "bye":
                return new ByeCommand();
            //Fallthrough

            default:
                return new UnknownCommand();
            //Fallthrough
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Description of a " + segments[0] + " cannot be empty");
        }
    }
}
