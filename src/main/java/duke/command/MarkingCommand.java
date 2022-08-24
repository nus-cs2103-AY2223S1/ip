package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

public class MarkingCommand extends Command {

    /** True if user wants to mark a task. False if is unmark */
    private boolean wantsToMark;

    private int taskID;

    public MarkingCommand(boolean wantsToMark, int taskID) {
        this.wantsToMark = wantsToMark;
        this.taskID = taskID;
    }

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

