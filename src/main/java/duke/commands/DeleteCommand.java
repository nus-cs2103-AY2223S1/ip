package duke.commands;

import duke.data.TaskList;
import duke.data.exceptions.InvalidTaskException;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to delete a Task from the list of tasks.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    /**
     * Constructor for a DeleteCommand.
     * @param taskNum The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        super();
        taskIndex = taskNum - 1;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task removed and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Task has been removed and the number of tasks on the list.
     * @throws InvalidTaskException If an invalid task number is provided by the user.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, StorageException {
        Task deletedTask = taskList.deleteTask(taskIndex);
        storage.save(taskList);
        return ui.showTaskRemoved(deletedTask) + ui.showNumberOfTasks(taskList.numTasks());
    }
}
