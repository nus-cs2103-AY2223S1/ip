package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int taskIndex;

    /**
     * Creates a new instance of mark command.
     *
     * @param taskIndex The index of the task in the task list.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task as done and updates the local file.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        storage.writeAllTasksToFile(taskList);
        ui.showMarkTaskMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
