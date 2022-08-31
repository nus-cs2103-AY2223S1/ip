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

    private int index;

    /**
     * Constructor for DeleteCommand.
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
            throw new DukeException("OOPS!!! The index is invalid.");
        }
        Task deleted = tasks.delete(index);
        message = "Noted. I've removed this task:" + System.lineSeparator();
        message += "  " + deleted + System.lineSeparator();
        message += "Now you have " + tasks.getSize() + " tasks in the list.";
        return message;
    }
}
