package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * Represents a command in the Duke application.
 */
public abstract class Command {
    protected TaskList tasks;

    /**
     * Sets the data for the command.
     *
     * @param tasks Tasks associated with the command.
     */
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     *
     * @return Result of the execution.
     * @throws DukeException Exception that occurred during the execution of the command.
     */
    public abstract CommandResult execute() throws DukeException;
}
