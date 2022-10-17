package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * The UnmarkCommand class represents a Command that marks a given Task in
 * the TaskList as not done.
 *
 * @author Edric Yeo
 */
public class UnmarkCommand extends Command {

    private int idx;

    /**
     * Creates an UnmarkCommand instance, given an index.
     *
     * @param idx The index of the Task in the TaskList to be marked
     *            as not done.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a message that a Task has been marked as not done.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to indicate the task has been marked as not done.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmarkTask(idx);
        return ui.showUnmarkTask(task);
    }

    /**
     * Returns the String representation of a UnmarkCommand.
     *
     * @return String representation of a UnmarkCommand.
     */
    @Override
    public String toString() {
        return "Unmark command for index: " + this.idx;
    }
}
