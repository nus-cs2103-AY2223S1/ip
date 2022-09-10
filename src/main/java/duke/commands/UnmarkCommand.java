package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int taskIndex;

    /**
     * Creates a new instance of unmark command.
     *
     * @param taskIndex The index of the task in the task list.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task as undone and updates the local file.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        Task task = taskList.getTask(this.taskIndex);
        task.maskUndone();
        storage.writeAllTasksToFile(taskList);
        ui.showUnmarkTaskMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
