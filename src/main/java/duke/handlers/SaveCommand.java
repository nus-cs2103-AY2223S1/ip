package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.Storage;
import duke.models.TaskList;

/**
 * Represents a save command.
 */
public class SaveCommand implements DukeCommand {

    /**
     * Save all the tasks to disk.
     *
     * @param taskList The tasklist to be saved.
     * @param s The user input specifying the detail of the command.
     * @return The response containing message about the number of tasks saved.
     * @throws DukeException If an error occurs during saving.
     */
    @Override
    public DukeResponse run(TaskList taskList, String s) throws DukeException {
        int numOfTasksSaved = Storage.saveTaskToDisk(taskList);
        return new DukeResponse(String.format("Successful saved %d tasks to disk!", numOfTasksSaved));
    }
}
