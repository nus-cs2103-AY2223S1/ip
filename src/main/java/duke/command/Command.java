package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class for commands.
 */
public abstract class Command {
    public abstract String getResponse(TaskList tasks, Storage storage);

    /**
     * Check if the command exit duke.
     * @return
     */
    public abstract boolean isExit();
}
