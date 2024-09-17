package duke.command;

import java.io.IOException;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that helps to mark or unmark tasks in the list */
public class MarkingCommand extends Command {

    /** True if user wants to mark a task. False if is unmark */
    private boolean wantsToMark;

    /** ID of the task that needs to be mark or unmark */
    private int taskID;

    /**
     * Initialises the object and set wantsToMark and taskID.
     * @param wantsToMark
     * @param taskID
     */
    public MarkingCommand(boolean wantsToMark, int taskID) {
        this.wantsToMark = wantsToMark;
        this.taskID = taskID;
    }

    /**
     * Marks the task if wantsToMark is true. Unmark otherwise.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskID <= 0 || taskID > taskList.getSize()) {
            throw new DukeException("Invalid task ID. You have " + taskList.getSize() + " tasks now.");
        }
        if (wantsToMark == true) {
            taskList.markTheTask(taskID);
            storage.updateFile(taskList);
            return ui.showMarkTaskMessage(taskList.getTask(taskID));
        } else {
            taskList.unmarkTheTask(taskID);
            storage.updateFile(taskList);
            return ui.showUnmarkTaskMessage(taskList.getTask(taskID));
        }
    }

}
