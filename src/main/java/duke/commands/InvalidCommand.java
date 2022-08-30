package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
