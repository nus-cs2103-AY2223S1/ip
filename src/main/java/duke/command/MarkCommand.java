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
    private static final String INVALID_INDEX = "OOPS!!! The index is invalid.";
    private static final String MARK_MSG = "OK, I've marked this task as done:"
            + System.lineSeparator() + "  ";
    private static final String MARKED_ALREADY_MSG = "This task was already done."
            + System.lineSeparator() + "  ";
    private int index;

    /**
     * Initializes a MarkCommand object.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the indexed task as done.
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
        if (curr.getStatusIcon().equals(" ")) {
            curr.markTask();
            message = MARK_MSG + curr + System.lineSeparator();
        } else {
            message = MARKED_ALREADY_MSG + curr + System.lineSeparator();
        }
        return message;
    }
}
