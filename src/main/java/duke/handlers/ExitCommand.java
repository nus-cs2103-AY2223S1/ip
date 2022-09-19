package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.storage.Storage;
import duke.models.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand implements DukeCommand {
    /**
     * Exit the program after saving the tasks to disk.
     * @param taskList The tasklist to be saved.
     * @param s The user input specifying the detail of the command.
     * @return The response representing and exit response.
     * @throws DukeException If an error occurs during saving tasks to disk.
     */
    @Override
    public DukeResponse run(TaskList taskList, String s) throws DukeException {
        Storage.saveTaskToDisk(taskList);
        return new DukeResponse(null, true);
    }
}
