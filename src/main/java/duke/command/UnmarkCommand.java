package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to unmark a task
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.getTask(index);
        if (t.getStatusIcon() != "X") {
            throw new DukeException("Task is not marked as done!");
        } else {
            t.markAsNotDone();
            storage.rewrite(tasks);
        }
        return ui.showTaskUnmarkMessage(t);
    }
}
