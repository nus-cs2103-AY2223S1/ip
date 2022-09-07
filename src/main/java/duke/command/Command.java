package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an executable command
 */
public abstract class Command {
    /**
     * Returns a string denoting command outcome after execution.
     *
     * @param tasks TaskList object to update.
     * @param storage Storage object to manage save file.
     * @return String denoting command outcome after execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
