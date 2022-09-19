package pony.command;

import pony.Storage;
import pony.TaskList;
import pony.Ui;

/**
 * Command class for various commands.
 */
public abstract class Command {
    // Default program exit status is set to false.
    protected boolean isExit = false;

    /**
     * Executes a command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui);

    /**
     * Checks if command should lead to the program exiting.
     *
     * @return A boolean indicating if program should exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
