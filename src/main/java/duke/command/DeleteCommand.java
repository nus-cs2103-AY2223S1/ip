package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete a {@code Task} at the given index
 */
public class DeleteCommand extends Command {

    private Task task;
    private int idx;

    /**
     * Constructor for {@code DeleteCommand}
     * @param idx the index
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * To execute the {@code DeleteCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            task = tasks.get(idx);
            tasks.remove(idx);
            ui.deleteTask(tasks, task);
            storage.update(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }

    }
}
