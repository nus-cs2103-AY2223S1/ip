package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String SHORTER_COMMAND_WORD = "u";

    private final int taskIndex;

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
     * @return The success message after marking a task as undone.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(this.taskIndex);
        task.maskUndone();
        storage.writeAllTasksToFile(taskList);
        return ui.getUnmarkCommandMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
