package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that deletes tasks from the list */
public class DeleteCommand extends Command {

    /** ID of the task that needs to be deleted */
    private int taskID;

    /**
     * Initialises the object and set the taskID.
     *
     * @param taskID
     */
    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Deletes the task from the list and displays a message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.getTask(taskID);
        taskList.deleteFromList(taskID);
        return ui.showRemovingTaskMessage(t, taskList.getSize());
    }
}
