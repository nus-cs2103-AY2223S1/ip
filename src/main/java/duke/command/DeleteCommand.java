package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a specific task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String REMOVE_TASK_MSG = "Noted. I've removed this task:\n ";
    public static final String NO_TASK_MSG = "There is no task at this index";
    private int toDelete;

    /**
     * Constructor for the DeleteCommand.
     * @param toDelete The index of the task to be deleted.
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete - 1;
    }

    /**
     * Deletes the specific task in the task list and return a message that the task
     * has been deleted.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the task has been deleted if successfully deleted.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        try {
            Task taskToDelete = task.getTask(toDelete);
            assert taskToDelete == null : "Task to be deleted should not be empty";
            task.deleteTask(toDelete);
            return REMOVE_TASK_MSG + taskToDelete.taskInfo();
        } catch (IndexOutOfBoundsException e) {
            return NO_TASK_MSG;
        }
    }

}
