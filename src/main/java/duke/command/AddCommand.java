package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a task to the task list
 *
 * @author Rama Aryasuta Pangestu
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs a command to add task <code>task</code> to the task list.
     *
     * @param task the task added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding <code>task</code> to the task list.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     * @throws DukeException if an error occurs when saving the task list to the save file
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        ui.addOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.addOutput("Now you have " + taskList.size() + " tasks in the list.\n");
        storage.save(taskList);
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
