package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to unmark a {@code Task} at the given index
 */
public class UnmarkCommand extends Command {
    private Task task;

    private int idx;

    /**
     * Constructor for {@code UnmarkCommand}
     * @param idx the index
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * To execute the {@code UnmarkCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            task = tasks.get(idx);
            task.markAsNotDone();
            ui.unmarkTask(task);
            storage.update(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }
    }
}
