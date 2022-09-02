package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param storage the storage
     * @throws DukeException if the index is out of range
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        tasks.get(index).setStatus(false);
        storage.writeFile(tasks);
        return "OK, I've marked this task as not done yet:\n"
                + tasks.get(index).toString();
    }
}
