package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of the command of marking tasks as done.
 *
 * @author Sun Ruoxin
 */
public class MarkCommand extends Command {
    /** The index of the task to be marked as done. */
    protected int index;

    /**
     * Class constructor.
     *
     * @param index the index of the task
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Marks the task as done.
     * Rewrites the file in the hard disk.
     * Shows the feedback to the user.
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
        tasks.get(index).setStatus(true);
        storage.writeFile(tasks, ui);
        ui.markMessage(tasks, index);
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
