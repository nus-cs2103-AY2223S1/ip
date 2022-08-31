package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Interface that represents the command to be implemented.
 */
public interface Command {
    /**
     * Runs the command with the TaskList and Storage.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @throws DukeException If any error occurs.
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException;
}
