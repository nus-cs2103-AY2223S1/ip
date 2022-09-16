package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Removes a {@link Task} from the {@link TaskList}
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Initializes the DeleteCommand to delete a task from the {@link TaskList}
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = tasks.getTask(this.index);
        tasks.deleteTask(this.index);
        storage.rewrite(tasks);
        return ui.showTaskDeletedMessage(t, tasks.getSize());
    }
}
