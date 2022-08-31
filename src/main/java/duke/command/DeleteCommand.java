package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to delete a task from the task list.
 *
 * @author Tan Jun Wei
 */
public class DeleteCommand extends Command {
    /** Index of task to be deleted by this command */
    private int index;

    /**
     * Constructs a new DeleteCommand with the given index.
     *
     * @param index Index of task to be deleted by this command.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the given index from the task list.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteItem(index);
        storage.saveAllTasks(tasks);
        ui.showOutput("OK, I've added the following task:\n  " + task + "\n");
        ui.showOutput("Now you have " + tasks.size() + " tasks in the list.");
    }
}
