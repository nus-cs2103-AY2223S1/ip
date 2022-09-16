package duke.command;

import duke.exception.DukeException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to mark a Task as done.
 */
public class MarkCommand extends Command {

    private int taskNumber;

    /**
     * A constructor for a MarkCommand.
     *
     * @param taskNumber The task number of the relevant Task in the TaskList.
     */
    public MarkCommand(int taskNumber) {
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
            taskList.mark(this.taskNumber);
            storage.writeToFile(taskList);
            response = Ui.mark(taskList.getTask(this.taskNumber));
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            response = "Invalid task number!";
        }

        assert response != null : "Response should not be null";
        return response;
    }
}
