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

    private final static String COMMAND_BYE =  "bye";
    private final static String COMMAND_LIST =  "list";
    private final static String COMMAND_MARK =  "mark";
    private final static String COMMAND_UNMARK =  "unmark";
    private final static String COMMAND_DELETE =  "delete";
    private final static String COMMAND_DEADLINE =  "deadline";
    private final static String COMMAND_EVENT =  "event";
    private final static String COMMAND_TODO =  "todo";
    private final static String COMMAND_FIND =  "find";

    /**
     * Parse input to return command for to be executed.
     *
     * @param fullCommand input taken from Ui.
     * @return command to be executed.
     */
    public static Command parse(String fullCommand) throws DukeUnknownCommandException {

        assert fullCommand != "";
        if (fullCommand.equals(COMMAND_BYE)) {
            return new ExitCommand(fullCommand);
        }

        if (fullCommand.equals(COMMAND_LIST)) {
            return new ReadCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_MARK)) {
            return new MarkCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_UNMARK)) {
            return new UnMarkCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DEADLINE)) {
            return new AddDeadlineCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_EVENT)) {
            return new AddEventCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_TODO)) {
            return new AddToDoCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_FIND)) {
            return new FindCommand(fullCommand);
        }

        throw new DukeUnknownCommandException();


    }
}
