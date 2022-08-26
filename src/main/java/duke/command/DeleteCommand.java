package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of the command of deleting tasks from the list.
 *
 * @author Sun Ruoxin
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    protected int index;

    /**
     * Class constructor.
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Deletes the task from the list.
     * Rewrites the file in the hard disk.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     * @throws DukeException if the index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        ui.deleteMessage(tasks, index, false);
        tasks.remove(index);
        storage.writeFile(tasks, ui);
        ui.deleteMessage(tasks, index, true);
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
