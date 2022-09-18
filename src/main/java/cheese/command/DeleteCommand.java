package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to delete a task.
 */
public class DeleteCommand extends Command {
    /** Index of task to delete using 0-based indexing. */
    private int taskIndex;

    /**
     * Constructs an instance of <code>DeleteCommand</code>.
     *
     * @param taskIndex Index of task to delete using 0-based indexing.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes operations to delete task from list and save the list.
     *
     * @throws CheeseException If given index of task to delete does not match a task from
     *                         <code>taskList</code>.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task deletedTask = taskList.delete(taskIndex);
        storage.save(taskList);
        return Response.getDeleteTaskMessage(deletedTask, taskList.getSize());
    }
}
