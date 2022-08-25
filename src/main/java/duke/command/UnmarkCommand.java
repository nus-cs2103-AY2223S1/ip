package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * UnmarkCommand is the Command when the user wants to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private int i;

    /**
     * Constuctor for UnmarkCommand.
     *
     * @param i The index of the task to be marked as not done.
     */
    public UnmarkCommand(int i) {
        this.i = i;
    }

    /**
     * Marks the ith task as not done.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (i < 0 || i >= tasks.getSize()) {
            throw new DukeException("OOPS!!! The index is invalid.");
        }
        Task curr = tasks.get(i);
        if (curr.getStatusIcon().equals("X")) {
            curr.unmarkTask();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("  " + curr);
        } else {
            ui.showMessage("This task has not been done in the first place.");
            ui.showMessage("  " + curr);
        }
    }
}
