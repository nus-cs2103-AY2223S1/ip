package iana.command;

import iana.exception.IanaException;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Represents a command that Iana can run.
 */
public abstract class Command {

    /**
     * Runs the command.
     * 
     * @param tasks the list of tasks.
     * @param ui the interface that handles interaction between Iana and user.
     * @param storage the storage that stores the user's list of tasks.
     * @return response to command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui) throws IanaException;

    /**
     * Returns a boolean of whether the command class is an exit command.
     * 
     * @return true if command is exit command.
     */
    public abstract boolean isExit();
}
