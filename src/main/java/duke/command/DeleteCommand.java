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
        super(false);
        this.index = index;
    }

    /**
     * A method that deletes a Task at the specified index, displays the delete-message, and updates the
     * Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @throws DukeException If TaskList is empty, or if index specified is out-of-bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("\tYou currently have no tasks in your list to delete.");
        } else {
            try {
                Task deletedTask = tasks.deleteTask(index);
                int size = tasks.getSize();
                ui.showDelete(deletedTask, size);
                storage.save(tasks.saveToStorage());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\tTask number does not exist!");
            }
        }
    }
}
