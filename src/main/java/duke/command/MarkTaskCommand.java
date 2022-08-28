package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskIndex);
        ui.showMarkTask(tasks.getTask(taskIndex));
        storage.write(tasks);
    }
}
