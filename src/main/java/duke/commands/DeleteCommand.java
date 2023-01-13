package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String SHORTER_COMMAND_WORD = "del";

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
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     * @return The success message after deleting a task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(this.taskIndex);
        taskList.removeTask(task);
        storage.writeAllTasksToFile(taskList);
        return ui.getDeleteCommandMessage(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
