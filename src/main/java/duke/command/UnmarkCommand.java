package duke.command;

import duke.exception.DukeException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to mark a Task as not done.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;

    /**
     * A constructor for an UnmarkCommand.
     * @param taskNumber The task number of the relevant Task in the TaskList.
     */
    public UnmarkCommand(int taskNumber) {
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
            taskList.unmark(this.taskNumber);
            storage.writeToFile(taskList);
            response = Ui.unmark(taskList.getTask(this.taskNumber));
        } catch (DukeException e) {
            response = e.getMessage();
        }

        assert response != null : "response should not be null";
        return response;
    }
}
