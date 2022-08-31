package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command that throws a DukeException.
 * This command is only used when there is an error.
 */
public class InvalidCommand extends Command {
    private final String errorMsg;

    /**
     * Initializes a new InvalidCommand instance.
     *
     * @param errorMsg Error message to be encapsulated by the command
     */
    public InvalidCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
