package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command is the order issued by the user.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns the verifier if Duke should close or not.
     *
     * @return true if Duke should exit, false if Duke should not.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Runs the command.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
