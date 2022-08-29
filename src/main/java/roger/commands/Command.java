package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;

/**
 * Encapsulates a command that the user gives to the Roger program.
 */
public abstract class Command {
    /**
     * Don't allow accessing constructor outside Command subclasses.
     */
    protected Command() {}

    /**
     * Execute the command and return any relevant information.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    public abstract String execute(TaskList tasks, Storage storage);

    /**
     * Returns true only if the command should cause Roger to exit.
     * @return true only if the command should cause Roger to exit.
     */
    public boolean isExit() {
        return false;
    }
}
