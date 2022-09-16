package duke.command;

import duke.exception.DukeException;
import duke.model.Task;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to add tasks into a TaskList.
 */
public class AddCommand extends Command {

    private Task createdTask;

    /**
     * A constructor for a AddCommand.
     *
     * @param task The Task associated with the command.
     */
    public AddCommand(Task task) {
        createdTask = task;
    }

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String response;
        try {
            taskList.add(createdTask);
            storage.writeToFile(taskList);
            response = Ui.add(createdTask);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        assert response != null : "Response should not be null";
        return response;
    }
}
