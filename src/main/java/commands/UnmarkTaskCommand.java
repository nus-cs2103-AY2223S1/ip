package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import ui.Ui;

public class UnmarkTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private int index;

    public UnmarkTaskCommand(Storage storage, Ui ui, TaskList tasks, int index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public boolean execute() {
        Task unmarkedTask = tasks.unmarkTask(index);
        ui.showUnmarkTaskResponse(unmarkedTask);
        storage.saveToFile(tasks.getList());
        return true;
    }

}
