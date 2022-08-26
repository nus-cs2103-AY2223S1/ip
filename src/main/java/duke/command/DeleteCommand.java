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
     * @param ui a ui to handle user interactions
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.delete(taskList.delete(this.taskNumber));
        storage.save(taskList);
    }
}
