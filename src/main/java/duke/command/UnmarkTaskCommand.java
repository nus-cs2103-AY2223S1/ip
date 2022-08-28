package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to mark a task as not done.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.markTaskAsNotDone(taskIndex);
        storage.write(tasks);
        return "Nice! I've marked this task as not done yet:\n\t" + tasks.getTask(taskIndex);
    }
}
