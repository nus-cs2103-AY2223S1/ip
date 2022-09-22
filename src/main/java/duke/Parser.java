package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddRecurringTaskCommand;
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

    private static final String INPUT_BYE = "bye";
    private static final String INPUT_DEADLINE = "^deadline.*";
    private static final String INPUT_DELETE = "^delete [0-9]*$";
    private static final String INPUT_EVENT = "^event.*";
    private static final String INPUT_FIND = "^find.*";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_MARK = "^mark [0-9]*$";
    private static final String INPUT_RECURRING = "^recurring.*";
    private static final String INPUT_TODO = "^todo.*";
    private static final String INPUT_UNMARK = "^unmark [0-9]*$";
    private static final String MESSAGE_INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :(";


    /**
     * Parses input into a <code>Command</code>.
     *
     * @param fullCommand User's input
     * @return <code>Command</code> to be executed.
     * @throws DukeException If input is not of a recognizable pattern.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equalsIgnoreCase(INPUT_BYE)) {
            return new ExitCommand(fullCommand);
        }
        if (fullCommand.equalsIgnoreCase(INPUT_LIST)) {
            return new ListCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_TODO)) {
            return new AddToDoCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_DEADLINE)) {
            return new AddDeadlineCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_EVENT)) {
            return new AddEventCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_RECURRING)) {
            return new AddRecurringTaskCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_MARK)) {
            return new MarkCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_UNMARK)) {
            return new UnmarkCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_DELETE)) {
            return new DeleteCommand(fullCommand);
        }
        if (fullCommand.matches(INPUT_FIND)) {
            return new FindCommand(fullCommand);
        }
        throw new DukeException(MESSAGE_INVALID_COMMAND);
    }
}
