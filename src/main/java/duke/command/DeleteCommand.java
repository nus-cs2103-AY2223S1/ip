package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param storage the storage
     * @throws DukeException if the index is out of range
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        String info = tasks.get(index).toString();
        tasks.remove(index);
        storage.writeFile(tasks);
        return "Noted. I've removed this task:\n"
                + "  " + info + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
