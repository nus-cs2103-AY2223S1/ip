package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * Constructor for a MarkCommand instance, given a Task.
     *
     * @param idx The index of the Task in the TaskList to be marked
     *            as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Method that marks the Task in the TaskList as done.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(idx);
        return ui.showMarkTask(task);
    }

    /**
     * Method that returns the String representation of a MarkCommand.
     *
     * @return String representation of a MarkCommand.
     */
    @Override
    public String toString() {
        return "Mark command for index: " + this.idx;
    }
}
