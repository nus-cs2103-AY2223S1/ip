package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract representation of a command.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage);

    public boolean isExit() {
        return false;
    }
}
