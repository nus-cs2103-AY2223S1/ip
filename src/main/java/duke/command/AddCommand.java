package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

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
