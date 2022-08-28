package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.markTaskAsDone(taskIndex);
        storage.write(tasks);
        return "Nice! I've marked this task as done:\n\t" + tasks.getTask(taskIndex);
    }
}
