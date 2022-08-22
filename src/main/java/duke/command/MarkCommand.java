package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * Executes the MarkCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the MarkCommand.
     * @param taskList the task list used by the MarkCommand.
     * @throws DukeException If Duke fails to execute the MarkCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        try {
            Task markedTask = taskList.markTaskWithIndex(this.index);
            ui.printTaskMarkSuccessMessage(markedTask);
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
