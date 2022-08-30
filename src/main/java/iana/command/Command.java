package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Represents a command that Iana can run.
 */
public abstract class Command {

    /**
     * Runs the command.
     * @param tasks the list of tasks.
     * @param ui the interface that handles interaction between Iana and user.
     * @param storage the storage that stores the user's list of tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean of whether the command class is an exit command.
     * @return true if command is exit command.
     */
    public abstract boolean isExit();
}
