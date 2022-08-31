package command;

import common.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmarkItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've unmarked the following task:\n  " + task);
    }
}
