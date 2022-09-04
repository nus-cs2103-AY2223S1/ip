package duke.command;

import duke.Storage;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;

import static java.util.Objects.isNull;

/**
 * Represents a command to search the specified task list for tasks containing
 * that specified keyword
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindTaskCommand
     *
     * @param keyword Search query to filter task list against
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes a FindTaskCommand by filtering the task list for the keyword and displaying the result.
     *
     * @param tasks The task list to search in
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     */
    @Override
    public Response<TaskList> execute(TaskList tasks, Storage storage) {
        TaskList filtered = tasks.filter((Task t) -> t.toString().contains(this.keyword));

        if (filtered.size() == 0) {
            return new Response<TaskList>(ResponseType.ERROR,
                    "uhoh- bobo can't find any matching tasks in your list...");
        } else {
            return new Response<TaskList>(ResponseType.LIST,
                    "sure thing! here are the matching tasks bobo found",
                    filtered);
        }
    }
}
