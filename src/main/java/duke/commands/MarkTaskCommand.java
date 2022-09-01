package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.ui.Ui;

public class MarkTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private int index;

    public MarkTaskCommand(Storage storage, Ui ui, TaskList tasks, int index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public boolean execute() {
        Task markedTask = tasks.markTask(index);
        ui.showMarkTaskResponse(markedTask);
        storage.saveToFile(tasks.getList());
        return true;
    }

}
