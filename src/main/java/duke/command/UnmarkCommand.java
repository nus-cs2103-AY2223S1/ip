package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

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
     * Executes the UnmarkCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the UnmarkCommand.
     * @param taskList the task list used by the UnmarkCommand.
     * @return the response pair.
     * @throws DukeException If Duke fails to execute the UnmarkCommand.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        try {
            Task unmarkedTask = taskList.unmarkTaskWithIndex(index);
            String responseMessage = "This task has been marked as not done yet:\n "
                    + unmarkedTask;
            ui.printMessage(responseMessage);
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
            return new Pair<>(true, responseMessage);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
