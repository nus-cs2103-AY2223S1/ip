package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskID;

    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteFromList(taskID);
        ui.showRemovingTaskMessage(taskList.getTask(taskID), taskList.getSize());
    }
}
