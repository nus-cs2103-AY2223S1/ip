package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;
import roger.tasks.Task;

/**
 * Encapsulates the command to mark a task as done.
 */
public class MarkCommand extends Command {
    protected int taskNum;

    /**
     * Create a MarkCommand.
     *
     * @param taskNum The taskNum of the task to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Mark the task as done.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task;
        try {
            task = tasks.get(this.taskNum);
        } catch (IndexOutOfBoundsException e) {
            return "That task does not exist!";
        }
        task.markAsDone();
        return "Fuiyoh, nephew so efficient! Finished this task:\n"
                + task.toString();
    }
}

