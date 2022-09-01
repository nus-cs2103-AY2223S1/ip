package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

/**
 * Handles the command 'unknown'.
 */
public class UnknownCommand extends Command {


    /**
     * Runs the command 'unknown'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for unknown input.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        throw new DukeException("I don't know what you are telling me to do");
    }
}
