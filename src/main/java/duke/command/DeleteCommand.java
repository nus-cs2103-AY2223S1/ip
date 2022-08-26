package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command
 * that will delete a task from Duke's task list.
 */
public class DeleteCommand extends Command {
    /** The number of the task to be deleted. */
    private final int n;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param n The number (as displayed in the full list) of the task to be deleted.
     */
    public DeleteCommand(int n) {
        this.n = n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(n);
        storage.writeToFile(tasks);
        ui.showDeleted(task, tasks);
    }
}
