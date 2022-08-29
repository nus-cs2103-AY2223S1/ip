package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;
import roger.tasks.Task;

/**
 * Encapsulates the command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    protected int taskNum;

    /**
     * Create an UnmarkCommand.
     *
     * @param taskNum The taskNum of the task to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Unmark the task as done.
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
        task.unmarkAsDone();
        return "Haven't done yet, mark what mark? Unmarked this task:\n"
                + task.toString();
    }
}

