package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a task from the list of tasks.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.deleteTask(taskIndex);
        ui.showDeleteTask(t, tasks);
        storage.write(tasks);
    }
}
