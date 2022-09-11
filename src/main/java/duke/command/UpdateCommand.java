package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UpdateCommand is a Command that updates the date a Task (Event or Deadline) in TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class UpdateCommand extends Command {
    private int index;
    private LocalDate newDate;

    /**
     * A constructor for UpdateCommand.
     *
     * @param index The index of the Task to be updated, with respect to the TaskList.
     * @param newDate The new date for the Task.
     */
    public UpdateCommand(int index, LocalDate newDate) {
        this.index = index;
        this.newDate = newDate;
    }

    /**
     * Updates the date of a Task at the specified index, displays the update-message, and updates the Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The update-message.
     * @throws DukeException If index specified is out-of-bounds or if Task is a ToDo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getTask(index).updateDate(newDate);
            storage.save(tasks.saveToStorage());
            return ui.showUpdate(tasks.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!");
        }
    }
}
