package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to unmark a task as completed.
 *
 * @author Tan Jun Wei
 */
public class UnmarkCommand extends Command {
    /** Index of task to be unmarked by this command */
    private int index;

    /**
     * Constructs a new UnmarkCommand with the given index.
     *
     * @param index The index of task to be unmarked by this command.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task at the given index as completed.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmarkItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've unmarked the following task:\n  " + task);
    }
}
