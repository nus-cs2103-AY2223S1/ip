package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    public static final String UNMARK_COMMAND = "unmark";

    public static final String MESSAGE_SUCCESS = "Got it. I've unmarked this task for you ;)\n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    private final int taskNumberToUnmark;

    public UnmarkCommand(int taskToMark) {
        this.taskNumberToUnmark = taskToMark;
    }

    @Override
    public String execute(List tasks, Ui ui, Storage storage) {
        try {
            assert taskNumberToUnmark >= 0;
            Task task = tasks.getTask(taskNumberToUnmark);
            task.markTaskAsNotDone();
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
