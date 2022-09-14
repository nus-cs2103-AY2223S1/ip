package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Encapsulation of a command.
 *
 * @author Sun Ruoxin
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks
     * @param storage the storage
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
