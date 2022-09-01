package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This interface encapsulates the information to be performed based on each command.
 */
public interface Command {
    /**
     * Executes command.
     * Returns a message, modifies TaskList, find or store tasks based on command executed.
     *
     * @param tasks TaskList to store tasks.
     * @param storage Storage to deal with loading tasks from the file and saving tasks in the file.
     * @return Message based on command executed.
     * @throws DukeException If there is an exception during the execution.
     */
    String execute(TaskList tasks, Storage storage) throws DukeException;
}
