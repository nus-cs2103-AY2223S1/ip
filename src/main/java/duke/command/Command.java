package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The task list.
     * @param ui The user interactions.
     * @param storage The task storage.
     * @throws DukeException If the command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether a command exits the Duke program.
     * @return True if command is an exit command; false otherwise.
     */
    public abstract boolean isExit();
}
