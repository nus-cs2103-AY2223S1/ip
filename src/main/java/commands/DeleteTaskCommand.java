package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import utils.Storage;

public class DeleteTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private int index;

    public DeleteTaskCommand(Storage storage, Ui ui, TaskList tasks, int index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public boolean execute() {
        Task deletedTask = tasks.deleteTask(index);
        ui.showDeleteTaskResponse(deletedTask, tasks);
        storage.saveToFile(tasks.getList());
        return true;
    }
}
