package duke.handlers;

import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents a list task command.
 */
public class ListTasksCommand implements DukeCommand {

    /**
     * List all tasks.
     * @param taskList The tasklist to be manipulated.
     * @param content The user input specifying the detail of the command.
     * @return The DukeReponse containing a list of all tasks
     */
    @Override
    public DukeResponse run (TaskList taskList, String content) {
        return new DukeResponse(taskList.toString());
    }
}
