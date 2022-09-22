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
    private static final String INVALID_INDEX = "OOPS!!! The index is invalid.";
    private static final String UNMARK_MSG = "OK, I've marked this task as not done yet:"
            + System.lineSeparator() + "  ";
    private static final String UNMARKED_ALREADY_MSG = "This task has not been done in the first place."
            + System.lineSeparator() + "  ";
    private int index;

    /**
     * Initializes an UnmarkCommand object.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the ith task as not done.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(INVALID_INDEX);
        }
        String message;
        Task curr = tasks.get(index);
        if (curr.getStatusIcon().equals("X")) {
            curr.unmarkTask();
            message = UNMARK_MSG + curr + System.lineSeparator();
        } else {
            message = UNMARKED_ALREADY_MSG + curr + System.lineSeparator();
        }
        return message;
    }
}
