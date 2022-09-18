package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to snooze a task.
 */
public class SnoozeCommand extends Command {
    /** Index of task to snooze using 0-based indexing. */
    private int taskIndex;

    /**
     * Constructs an instance of <code>SnoozeCommand</code>.
     *
     * @param taskIndex Index of task to snooze using 0-based indexing.
     */
    public SnoozeCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes operations to mark task as done and save the list.
     *
     * @throws CheeseException If given index of task to mark does not match a task from
     *                         <code>taskList</code> or there is an error in saving.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task taskSnoozed = taskList.snoozeTask(taskIndex);
        storage.save(taskList);
        return Response.getSnoozeTaskMessage(taskSnoozed);
    }
}
