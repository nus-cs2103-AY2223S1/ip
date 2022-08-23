package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents an executable duke command.
 */
public abstract class Command {
    protected boolean stillRunning = true;
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean isStillRunning() {
        return this.isStillRunning();
    }
}
