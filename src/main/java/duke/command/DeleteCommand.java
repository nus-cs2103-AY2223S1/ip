package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to delete a Task from a TaskList.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * A constructor for a DeleteCommand.
     *
     * @param taskNumber the task number of the Task to be deleted in the TaskList.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String response = Ui.delete(taskList.delete(this.taskNumber));
        storage.writeToFile(taskList);
        assert response != null : "response should not be null";
        return response;
    }
}
