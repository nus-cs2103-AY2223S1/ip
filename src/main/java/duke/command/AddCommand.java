package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addToList(task);
        ui.showAddingTaskMessage(task, taskList.getSize());
    }
}
