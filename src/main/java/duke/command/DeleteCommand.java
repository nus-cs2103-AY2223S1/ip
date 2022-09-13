package duke.command;

import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a DeleteCommand object to be called when user inputs 'delete'.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String TASK_DELETE = "Alright, I've removed this task:";
    private int index;

    /**
     * Constructs DeleteCommand object with the index of the element to delete.
     *
     * @param index index of the element to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes DeleteCommand by deleting the task, updating storage and display the deleted task.
     *
     * @param tasks list of task to delete the index from.
     * @param storage Updates the list of task after the deletion.
     * @return delete command message
     * @throws TaskNotFoundException when index given is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskNotFoundException {
        Task task = tasks.deleteTask(index);
        storage.update(tasks);
        return String.format("%s\n%s", TASK_DELETE, task);
    }
}
