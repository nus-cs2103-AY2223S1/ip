package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int taskIndex;

    /**
     * Creates a new instance of delete command.
     *
     * @param taskIndex The index of the task in the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task from the task list and the local file based on the task index.
     *
     * @param tasks The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        tasks.removeTask(task);
        storage.writeAllTasksToFile(tasks);
        ui.showRemoveTaskMessage(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
