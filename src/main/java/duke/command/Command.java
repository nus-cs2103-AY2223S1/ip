package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract representation of a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return Result of the execution.
     */
    public abstract String execute(TaskList tasks, Storage storage);

    /**
     * Checks whether the command should exit the app.
     *
     * @return A boolean representing whether the command should exit the app.
     */
    public boolean isExit() {
        return false;
    }
}
