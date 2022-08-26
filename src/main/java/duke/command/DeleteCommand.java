package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Removes Task from the task list
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = tasks.getTask(this.index);
        tasks.deleteTask(this.index);
        storage.rewrite(tasks);
        ui.showTaskDeletedMessage(t, tasks.getSize());
    }
}
