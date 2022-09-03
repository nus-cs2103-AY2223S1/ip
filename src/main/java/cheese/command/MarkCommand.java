package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to mark a task as done.
 */
public class MarkCommand extends Command {
    /** Index of task to mark as done using 0-based indexing. */
    private int taskIndex;

    /**
     * Constructs an instance of <code>MarkCommand</code>.
     *
     * @param givenIndex Index of task to mark as done using 1-based indexing.
     */
    public MarkCommand(int givenIndex) {
        taskIndex = givenIndex - 1;
    }

    /**
     * Executes operations to mark task as done and save the list.
     *
     * @throws CheeseException If given index of task to mark does not match a task from
     *                         <code>taskList</code>.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task taskDone = taskList.markTaskAsDone(taskIndex);
        storage.save(taskList);
        return Response.getMarkTaskAsDoneMessage(taskDone);
    }
}
