package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand is a Command that deletes a Task from the TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class DeleteCommand extends Command {
    private int index;

    /**
     * A constructor for DeleteCommand.
     *
     * @param index The index of the Task to be deleted, with respect to the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a Task at the specified index, displays the delete-message, and updates the
     * Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The delete-message.
     * @throws DukeException If TaskList is empty, or if index specified is out-of-bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("You currently have no tasks in your list to delete.");
        } else {
            try {
                Task deletedTask = tasks.deleteTask(index);
                int size = tasks.getSize();
                storage.save(tasks.saveToStorage());
                return ui.showDelete(deletedTask, size);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task number does not exist!");
            }
        }
    }
}
