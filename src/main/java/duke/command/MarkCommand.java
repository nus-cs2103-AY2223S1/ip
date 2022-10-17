package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * The MarkCommand class represents a Command that marks a given Task in
 * the TaskList as done.
 *
 * @author Edric Yeo
 */
public class MarkCommand extends Command {

    private int idx;

    /**
     * Creates a MarkCommand instance, given an index.
     *
     * @param idx The index of the Task in the TaskList to be marked as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a message that a Task has been marked as done.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to indicate the task has been marked as done.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTask(idx);
        return ui.showMarkTask(task);
    }

    /**
     * Returns the String representation of a MarkCommand.
     *
     * @return String representation of a MarkCommand.
     */
    @Override
    public String toString() {
        return "Mark command for index: " + this.idx;
    }
}
