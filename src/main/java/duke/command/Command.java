package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

/**
 * Abstract class for other commands to inherit from.
 */
public abstract class Command {

    /**
     * Runs a command based on its type.
     *
     * @param taskList the task list
     * @param builder
     * @throws DukeException Duke Exception
     */
    public abstract void run(TaskList taskList, Response builder) throws DukeException;
}
