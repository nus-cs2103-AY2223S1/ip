package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsNotDone(taskIndex);
        ui.showUnmarkTask(tasks.getTask(taskIndex));
        storage.write(tasks);
    }
}
