package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    /** ID of the task that needs to be deleted */
    private int taskID;

    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Deletes the task from the list and displays a message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteFromList(taskID);
        ui.showRemovingTaskMessage(taskList.getTask(taskID), taskList.getSize());
    }
}
