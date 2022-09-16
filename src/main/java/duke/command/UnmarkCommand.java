package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


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
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     * @throws DukeException if the index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            task = tasks.get(idx);
            task.markAsNotDone();
            storage.update(tasks.getTasks());
            return ui.unmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }
    }
}
