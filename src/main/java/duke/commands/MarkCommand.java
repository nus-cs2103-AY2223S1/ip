package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String SHORTER_COMMAND_WORD = "m";

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
     * @return The success message after marking a task as done.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        storage.writeAllTasksToFile(taskList);
        return ui.getMarkCommandMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
