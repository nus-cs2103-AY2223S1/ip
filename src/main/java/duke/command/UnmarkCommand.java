package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmark command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs a new instance of UnmarkCommand.
     *
     * @param description the description of the UnmarkCommand.
     * @throws DukeException If description is not valid.
     */
    public UnmarkCommand(String description) throws DukeException {
        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException();
        }
    }

    /**
     * Executes the UnmarkCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the UnmarkCommand.
     * @param taskList the task list used by the UnmarkCommand.
     * @throws DukeException If Duke fails to execute the UnmarkCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        try {
            Task unmarkedTask = taskList.unmarkTaskWithIndex(index);
            ui.printTaskUnmarkSuccessMessage(unmarkedTask);
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
