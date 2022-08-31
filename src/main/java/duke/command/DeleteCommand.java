package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.showOutput("Now you have " + tasks.size() + " tasks in the list.");
    }
}
