package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to unmark a task.
 */
public class UnmarkCommand extends Command {
    /** Index of task to unmark using 0-based indexing. */
    private int taskIndex;

    /**
     * Constructs an instance of <code>UnmarkCommand</code>.
     *
     * @param givenIndex Index of task to unmark using 1-based indexing.
     */
    public UnmarkCommand(int givenIndex) {
        taskIndex = givenIndex - 1;
    }

    /**
     * Executes operations to unmark task and save the list.
     *
     * @throws CheeseException If given index of task to unmark does not match a task from
     *                         <code>taskList</code> or there is an error in saving.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task taskNotDone = taskList.markTaskAsNotDone(taskIndex);
        storage.save(taskList);
        return Response.getMarkTaskAsNotDoneMessage(taskNotDone);
    }
}
