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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addItem(task);
        storage.saveTask(task);
        ui.showOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.showOutput("Now you have " + tasks.size() + " tasks in the list.");
    }
}
