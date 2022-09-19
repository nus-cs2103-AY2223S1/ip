package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command to list all Tasks stored by Duke.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int len = tasks.getLength();
        return "You currently have " + len + " tasks:\n" +
            tasks.toString();
    }
}
