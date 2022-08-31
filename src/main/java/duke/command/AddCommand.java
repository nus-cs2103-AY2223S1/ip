package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to add new task to task list.
 *
 * @author Tan Jun Wei
 */
public class AddCommand extends Command {
    /** The task to be added by this command */
    private Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param task the task to be added by this command
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add new task to task list.
     *
     * @param tasks The task list to be added to.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws duke.common.DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addItem(task);
        storage.saveTask(task);
        ui.showOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.showOutput("Now you have " + tasks.size() + " tasks in the list.");
    }
}
