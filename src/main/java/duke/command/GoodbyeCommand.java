package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command to terminate Duke.
 */
public class GoodbyeCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodbye();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
