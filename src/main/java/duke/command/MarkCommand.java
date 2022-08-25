package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * MarkCommand is the Command when the user wants to mark a task as done.
 */
public class MarkCommand extends Command {

    private int i;

    /**
     * Constructor for MarkCommand.
     *
     * @param i The index of the task to be marked as done.
     */
    public MarkCommand(int i) {
        this.i = i;
    }

    /**
     * Marks the ith task as done.
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
        if (curr.getStatusIcon().equals(" ")) {
            curr.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + curr);
        } else {
            ui.showMessage("This task was already done.");
            ui.showMessage("  " + curr);
        }
    }
}
