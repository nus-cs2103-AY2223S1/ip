package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a task to the list of tasks.
 *
 * @author Derrick Khoo
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a command by adding <code>Task</code> to the list of tasks.
     *
     * @param task the task to be added to the list of tasks.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Handles the command by adding <code>Task</code> to the list of tasks.
     *
     * @param storage   the <code>Storage</code> that loads and saves tasks in a file
     * @param ui        the user interface dealing with user inputs
     * @param taskList  the list of tasks
     * @throws DukeException if an error occurs when adding the task to the list of tasks.
     */
    @Override
    public String handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        taskList.addTask(this.task);
        return "Duke says:\n" + ui.formatMessage("Got it. I've added this task:\n"
                + this.task + "\n" + taskList.getArraySize());
    }
}
