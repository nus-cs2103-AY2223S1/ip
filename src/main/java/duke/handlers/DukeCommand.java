package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents a command.
 */
public interface DukeCommand {
    /**
     * Executes the command.
     *
     * @param taskList The tasklist to be manipulated.
     * @param content The user input specifying the detail of the command.
     * @return The response.
     * @throws DukeException If any errors occur during the execution.
     */
    DukeResponse run(TaskList taskList, String content) throws DukeException;
}
