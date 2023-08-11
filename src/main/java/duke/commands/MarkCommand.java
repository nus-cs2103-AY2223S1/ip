package duke.commands;

import duke.data.TaskList;
import duke.data.exceptions.InvalidTaskException;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to mark a Task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructor for a MarkCommand.
     * @param taskNum The task number of the task to be marked as done.
     */
    public MarkCommand(int taskNum) {
        super();
        taskIndex = taskNum - 1;
    }

    /**
     * Marks a task as done.
     * @param taskList List of tasks.
     * @param ui Shows the Task marked as done.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Task has been marked as done.
     * @throws InvalidTaskException If an invalid task number is provided by the user.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, StorageException {
        Task markedTask = taskList.changeTaskStatus(taskIndex, true);
        storage.save(taskList);
        return ui.showTaskDone(markedTask);
    }
}
