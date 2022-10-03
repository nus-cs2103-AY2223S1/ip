package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    public static final String MARK_COMMAND = "mark";

    public static final String MESSAGE_SUCCESS = "Congrats on completing this task!\n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    private final int taskNumberToMark;

    public MarkCommand(int taskToMark) {
        this.taskNumberToMark = taskToMark;
    }
    public int getTaskToMark() {
        return taskNumberToMark;
    }

    @Override
    public String execute(List tasks, Ui ui, Storage storage) {
        try {
            assert taskNumberToMark >= 0;
            Task task = tasks.getTask(taskNumberToMark);
            task.markTaskAsDone();
            storage.save();
            return ui.showToUser(String.format(MESSAGE_SUCCESS, task, tasks.numberOfTasks()));
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

}
