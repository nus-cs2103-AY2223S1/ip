package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * The DeleteCommand class represents a Command that deletes a
 * Task from the TaskList.
 *
 * @author Edric Yeo
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Creates a DeleteCommand instance, given a Task to be deleted.
     *
     * @param idx The index of Task to be deleted from the TaskList.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a message to indicate a Task has been deleted.
     * Removes given Task from the TaskList.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to indicate a Task has been deleted.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deleted = tasks.deleteTask(idx);
        return ui.showDeleteTask(deleted, tasks);
    }

    /**
     * Returns the String representation of a DeleteCommand.
     *
     * @return String representation of a DeleteCommand.
     */
    @Override
    public String toString() {
        return "Delete command of index: " + idx;
    }
}
