package duke.command;

import duke.exception.DukeException;
import duke.processor.TaskList;

/**
 * Class to represent commands.
 *
 * @author Melissa Anastasia Harijanto
 */
public abstract class Command {
    /**
     * Executes a command to the list of tasks.
     *
     * @param tasks The list of tasks where the command is executed.
     * @throws DukeException Exception that will be thrown.
     */
    public abstract void execute(TaskList tasks) throws DukeException;
}
