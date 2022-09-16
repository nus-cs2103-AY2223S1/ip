package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Command to mark a {@code Task} at the given index
 */
public class MarkCommand extends Command {

    private Task task;
    private int idx;

    /**
     * Constructor for {@code MarkCommand}
     * @param idx the index
     */
    public MarkCommand(int idx) {

        this.idx = idx;
    }

    /**
     * To execute the {@code MarkCommand}
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
            task.markAsDone();
            storage.update(tasks.getTasks());
            return ui.markTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }
    }
}
