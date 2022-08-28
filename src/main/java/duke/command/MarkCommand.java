package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is a Command that marks a Task in TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class MarkCommand extends Command {
    private int index;

    /**
     * A constructor for MarkCommand.
     * @param index The index of the Task to be marked, with respect to the TaskList.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that marks a Task at the specified index, displays the mark-message, and updates the Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The mark-message.
     * @throws DukeException If index specified is out-of-bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTask(index);
            storage.save(tasks.saveToStorage());
            return ui.showMark(tasks.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!");
        }
    }
}
