package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class that is the parent class of all commands.
 */
public abstract class Command {
    protected boolean terminated;

    public Command() {
        this.terminated = false;
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     */
    public abstract void execCommand(TaskList list, Ui ui, Storage storage);
}
