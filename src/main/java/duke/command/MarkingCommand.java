package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that helps to mark or unmark tasks in the list */
public class MarkingCommand extends Command {

    /** True if user wants to mark a task. False if is unmark */
    private boolean wantsToMark;

    /** ID of the task that needs to be mark or unmark */
    private int taskID;

    public MarkingCommand(boolean wantsToMark, int taskID) {
        this.wantsToMark = wantsToMark;
        this.taskID = taskID;
    }

    /**
     * Mark the task if wantsToMark is true. Unmark otherwise.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (wantsToMark == true) {
            taskList.markTheTask(taskID);
            ui.showMarkTaskMessage(taskList.getTask(taskID));
        } else {
            taskList.unmarkTheTask(taskID);
            ui.showUnmarkTaskMessage(taskList.getTask(taskID));
        }
    }
}
