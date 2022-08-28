package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a task from the list of tasks.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task del = tasks.deleteTask(taskIndex);
        storage.write(tasks);
        return "Noted. I've removed this task:\n\t" + del + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
