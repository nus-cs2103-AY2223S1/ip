package duke.command;

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
     * @param taskNumber
     */
    public UnmarkCommand(int taskNumber) {
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
        taskList.unmark(this.taskNumber);
        storage.writeToFile(taskList);
        String response = Ui.unmark(taskList.getTask(this.taskNumber));
        return response;
    }
}
