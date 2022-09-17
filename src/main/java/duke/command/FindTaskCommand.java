package duke.command;

import duke.Storage;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to search the specified task list for tasks containing
 * that specified keyword.
 */
public class FindTaskCommand extends Command {
    private static final String MATCHING_TASK_FOUND_SUCCESS_MESSAGE = "sure thing!"
            + " here are the matching tasks bobo found";
    private static final String NO_MATCHING_TASK_ERROR_MESSAGE = "uh-oh."
            + " bobo can't find any matching tasks in your list...";
    /** The keyword to be used to search the task list */
    private final String keyword;

    /**
     * Constructs a FindTaskCommand.
     *
     * @param keyword Search query to filter task list against.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes a FindTaskCommand by filtering the task list for the keyword and displaying the result.
     *
     * @param tasks The task list to search in.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return A Response containing the task list filtered by the specified keyword, with Response
     *         type list if there are matching tasks, and Response type error if there are no matching
     *         tasks found. Also contains a tasks successfully found or no task match error
     *         response message.
     */
    @Override
    public Response<TaskList> execute(TaskList tasks, Storage storage) {
        // get a filtered task list containing only tasks that contain the specified keyword
        TaskList filtered = tasks.filter((Task t) -> t.toString().contains(keyword));
        boolean hasNoMatchingTasks = filtered.size() == 0;
        if (hasNoMatchingTasks) {
            return new Response<>(ResponseType.ERROR, NO_MATCHING_TASK_ERROR_MESSAGE, null);
        }
        return new Response<>(ResponseType.LIST, MATCHING_TASK_FOUND_SUCCESS_MESSAGE, filtered);
    }
}
