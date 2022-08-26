package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of the command of unmarking tasks as done.
 *
 * @author Sun Ruoxin
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be unmarked. */
    protected int index;

    /**
     * Class constructor.
     *
     * @param index the index of the task to be marked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Unmarks the task as done.
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
        tasks.get(index).setStatus(false);
        storage.writeFile(tasks, ui);
        ui.unmarkMessage(tasks, index);
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
