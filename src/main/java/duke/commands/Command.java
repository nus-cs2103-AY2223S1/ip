package duke.commands;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;

/**
 * Generic command representing a possible user input.
 */
public abstract class Command {

    /**
     * Performs the command on the actual list of tasks.
     * @param tasks List of tasks to perform command on.
     * @throws DukeException if command fails to run properly
     */
    public abstract Message execute(TaskList tasks) throws DukeException;
}
