package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.models.Task;

/**
 * Removes Task from the task list
 */
public class DeleteCommand extends Command {
    private final Task toDelete;

    public DeleteCommand(Task task) { this.toDelete = task; }

    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
