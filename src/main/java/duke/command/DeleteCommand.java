package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand is the Command when the user wants to delete a task.
 */
public class DeleteCommand extends Command {
    private static final String INVALID_INDEX = "OOPS!!! The index is invalid.";
    private static final String DELETE_MSG = "Noted. I've removed this task:"
            + System.lineSeparator() + "  ";
    private int index;

    /**
     * Initializes a DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the ith task.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message;
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(INVALID_INDEX);
        }
        Task deleted = tasks.delete(index);
        message = DELETE_MSG + deleted + System.lineSeparator();
        message += "Now you have " + tasks.getSize() + " tasks in the list.";
        return message;
    }
}
