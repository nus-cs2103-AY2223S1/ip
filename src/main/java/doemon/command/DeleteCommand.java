package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(this.taskIndex);
        ui.showDeleteTask(task, tasks.getSize());
        storage.deleteTaskData(this.taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
