package commands;

import tasks.TaskList;
import tasks.Task;
import ui.Ui;
import storage.Storage;

public class DeleteCommand extends Command {

    private int num;

    public DeleteCommand(int num) {
        this.num = num - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(this.num);
        ui.showDeleted(tasks, task);
        storage.updateStorage(tasks);
    }
}
