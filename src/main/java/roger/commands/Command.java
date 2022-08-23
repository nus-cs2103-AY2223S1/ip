package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;

/**
 * Encapsulates a command that the user gives to the Roger program.
 */
public abstract class Command {
    /**
     * Don't allow accessing constructor outside Command subclasses.
     */
    protected Command() {}

    /**
     * Execute the command and show the end user and relevant information.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true only if the command should cause Roger to exit.
     * @return true only if the command should cause Roger to exit.
     */
    public boolean isExit() {
        return false;
    }
}
