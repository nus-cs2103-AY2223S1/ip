package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand is a Command that un-marks a Task in TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class UnmarkCommand extends Command {
    private int index;

    /**
     * A constructor for UnmarkCommand.
     * @param index The index of the Task to be un-marked, with respect to the TaskList.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that un-marks a Task at the specified index, displays the un-mark-message, and updates the Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The un-mark-message.
     * @throws DukeException If index specified is out-of-bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmarkTask(index);
            storage.save(tasks.saveToStorage());
            return ui.showUnmark(tasks.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!");
        }
    }
}
