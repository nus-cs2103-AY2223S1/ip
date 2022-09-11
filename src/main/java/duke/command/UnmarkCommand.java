package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a specific task as not done in the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String UNMARK_TASK_MSG = "Ok, I've marked this task as not done yet:\n ";
    public static final String NO_TASK_MSG = "There is no task at this index";
    private int toUnmark;

    /**
     * Constructor for the UnmarkCommand Object.
     *
     * @param toUnmark The index of the task to be mark as not done.
     */
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark - 1;
    }

    /**
     * Marks the specific task as not done in the task list and return a message that the task
     * has been mark as not done.
     *
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the task has been mark as done if successfully mark as not done.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        try {
            task.unmarkTask(toUnmark);
            Task taskToUnmark = task.getTask(toUnmark);
            assert taskToUnmark == null : "Task to be mark not done should not be empty";
            return UNMARK_TASK_MSG + taskToUnmark.taskInfo();
        } catch (IndexOutOfBoundsException e) {
            return NO_TASK_MSG;
        }
    }

}
