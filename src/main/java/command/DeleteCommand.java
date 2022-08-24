package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int indexOfTask;
    public DeleteCommand(int indexOfTask) {
        super(false);
        this.indexOfTask = indexOfTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(indexOfTask);
        ui.deleteTask(task);
        ui.displayNumberOfTasks(tasks.getNumberOfTasks());
        storage.writeToFile(tasks);
    }
}
