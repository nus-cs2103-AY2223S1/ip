package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;
import roger.tasks.Task;

/**
 * Encapsulates the command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    protected int taskNum;

    /**
     * Create a DeleteTaskCommand.
     *
     * @param taskNum The taskNum of the task to be deleted.
     */
    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Delete the task.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.delete(taskNum);
        return "Haiya so lazy. Deleted this task:\n"
                + task.toString() + "\n"
                + "Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.";
    }
}
