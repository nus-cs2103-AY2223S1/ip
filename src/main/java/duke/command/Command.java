package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that can be executed.
 */
abstract public class Command {

    /**
     * Executes this command.
     * @param tasks Task list to be altered during the execution.
     * @param storage Storage to store results of the execution to.
     * @return The response of the execution.
     * @throws DukeException If user enters an invalid input.
     */
    public abstract Response execute(TaskList tasks, Storage storage) throws DukeException;

}
