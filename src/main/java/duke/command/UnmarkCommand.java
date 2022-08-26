package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The UnmarkCommand class represents a command
 * that marks a task in Duke as done.
 */
public class UnmarkCommand extends Command {
    /** The number of the task to be marked as not done. */
    private final int n;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param n The number (as displayed in the full list) of the task to be marked as not done.
     */
    public UnmarkCommand(int n) {
        this.n = n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(n);
        storage.writeToFile(tasks);
        ui.showUnmarked(task);
    }
}
