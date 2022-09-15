package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to mark a <code>Task</code> as not done.
 */
public class UnmarkCommand extends Command {

    private static final int DESCRIPTION_INDEX = 7;

    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "OOPS!!! You cannot mark a non-existent task as undone.";
    private static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:\n %s";

    /**
     * Constructs a <code>UnMarkCommand</code> command.
     *
     * @param description Input from the user.
     */
    public UnmarkCommand(String description) {
        super(description);
    }

    /**
     * Marks the user-specified <code>Task</code> as not done.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeIndexOutOfBoundsException If user inputted an index outside the range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeIndexOutOfBoundsException {
        try {
            int index = changeToZeroIndex(Integer.parseInt(description.substring(DESCRIPTION_INDEX)));
            assert index >= 0 : MESSAGE_INDEX_OUT_OF_BOUNDS;
            assert index < tasks.getSize() : MESSAGE_INDEX_OUT_OF_BOUNDS;
            tasks.markTaskAsUndone(index);
            Task task = tasks.get(index);
            String response = String.format(MESSAGE_SUCCESS, task);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
    }

    private int changeToZeroIndex(int index) {
        return index - 1;
    }
}
