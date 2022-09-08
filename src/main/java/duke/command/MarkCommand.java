package duke.command;

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
     * @param taskNumber the task number of the relevant Task in the TaskList.
     */
    public MarkCommand(int taskNumber) {
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
        taskList.mark(this.taskNumber);
        storage.writeToFile(taskList);
        String response = Ui.mark(taskList.getTask(this.taskNumber));
        return response;
    }
}
