package duke.command;

import duke.exception.DukeException;
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
     * @param taskNumber The task number of the Task to be deleted in the TaskList.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
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
            response = Ui.delete(taskList.delete(this.taskNumber));
            storage.writeToFile(taskList);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        assert response != null : "Response should not be null";
        return response;
    }
}
