package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

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
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            task = tasks.get(idx);
            task.markAsDone();
            ui.markTask(task);
            storage.update(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }
    }
}
