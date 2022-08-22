package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a new instance of DeleteCommand.
     *
     * @param description the description of the DeleteCommand.
     * @throws DukeException If description is not valid.
     */
    public DeleteCommand(String description) throws DukeException {
        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException();
        }
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the DeleteCommand.
     * @param taskList the task list used by the DeleteCommand.
     * @throws DukeException If Duke fails to execute the DeleteCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        try {
            Task deletedTask = taskList.removeTaskWithIndex(this.index);
            ui.printTaskDeletionSuccessMessage(deletedTask, taskList.getTaskListSize());
            storage.saveTasksInStorage(taskList.toStorageRepresentation());
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException();
        }
    }
}
