package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.addItem(task);
        ui.showOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.showOutput("Now you have " + taskList.size() + " tasks in the list.\n");
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
