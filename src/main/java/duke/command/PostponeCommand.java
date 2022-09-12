package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to postpone a task
 */
public class PostponeCommand extends Command {
    private final int index;

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
