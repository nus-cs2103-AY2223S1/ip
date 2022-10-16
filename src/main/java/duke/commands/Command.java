package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a <code>Command</code> that can be executed
 */
public abstract class Command {
    /**
     * Executes the command
     * @param storage The <code>Storage</code> that manages a file
     * @param tl The <code>TaskList</code> that manages the list of tasks
     * @return A string to display to the user
     * @throws DukeException If the user passes an invalid command
     */
    public abstract String execute(Storage storage, TaskList tl) throws DukeException;
}
