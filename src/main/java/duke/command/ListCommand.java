package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to list out all Tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String response = Ui.list(taskList);
        assert response != null : "response should be null";
        return response;
    }
}
