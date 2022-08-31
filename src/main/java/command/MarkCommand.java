package command;

import common.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've marked the following task:\n  " + task);
    }
}
