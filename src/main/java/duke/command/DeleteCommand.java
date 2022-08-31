package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
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
        taskList.deleteFromList(taskID);
        return ui.showRemovingTaskMessage(taskList.getTask(taskID), taskList.getSize());
    }
}
