package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The MarkCommand class represents a command
 * that marks a task in duke.Duke as done.
 */
public class MarkCommand extends Command {
    /** The number of the task to be marked as done. */
    private final int n;

    /**
     * Constructs a new MarkCommand.
     *
     * @param n The number (as displayed in the full list) of the task to be marked as done.
     */
    public MarkCommand(int n) {
        this.n = n;
    }

    /**
     * Marks a task in Duke as done.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(n);
        storage.writeToFile(tasks);
        ui.showMarked(task);
    }
}
