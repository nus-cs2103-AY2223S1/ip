package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(this.taskIndex);
        ui.showMarkTask(task);
        storage.markTaskData(this.taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
