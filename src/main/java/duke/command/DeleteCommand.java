package duke.command;

import duke.storage.Storage;
import duke.TaskList;
import duke.models.Task;

/**
 * Removes Task from the task list
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) { this.index = index; }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.deleteTask(index);
        storage.rewrite(tasks);
    }
}
