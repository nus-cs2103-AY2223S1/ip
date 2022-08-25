package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * Constructor for a UnmarkCommand instance, given a Task.
     *
     * @param idx The index of the Task in the TaskList to be marked
     *            as not done.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Method that marks the Task in the TaskList as not done.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmarkTask(idx);
        ui.showUnmarkTask(task);
    }

    /**
     * Method that returns the String representation of a UnmarkCommand.
     *
     * @return String representation of a UnmarkCommand.
     */
    @Override
    public String toString() {
        return "Unmark command for index: " + this.idx;
    }
}
