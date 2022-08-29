package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Mark command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a new instance of MarkCommand.
     *
     * @param description the description of the MarkCommand.
     * @throws DukeException If description is not valid.
     */
    public MarkCommand(String description) throws DukeException {
        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException();
        }
    }

    /**
     * Executes the MarkCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the MarkCommand.
     * @param taskList the task list used by the MarkCommand.
     * @return the response pair.
     * @throws DukeException If Duke fails to execute the MarkCommand.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        try {
            Task markedTask = taskList.markTaskWithIndex(this.index);
            String responseMessage = "This task has been marked as done:\n "
                    + markedTask;
            ui.printMessage(responseMessage);
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
            return new Pair<>(true, responseMessage);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
