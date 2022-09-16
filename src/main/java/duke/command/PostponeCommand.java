package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Postpones a {@link Task} in the {@link TaskList}
 */
public class PostponeCommand extends Command {
    private final int index;

    /**
     * Initializes the PostponeCommand to postpone a task
     * @param index Index of the task to be postponed
     */
    public PostponeCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = tasks.getTask(index);
        t.postponeTask();
        return ui.showPostponedTask(t);
    }
}
