package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to delete a <code>Task</code>.
 */
public class DeleteCommand extends Command {

    private static final int DESCRIPTION_INDEX = 7;

    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "OOPS!!! You cannot delete a non-existent task.";
    private static final String MESSAGE_SUCCESS = "Noted. I've removed this task:\n  %s";

    /**
     * Constructs a <code>DeleteCommand</code> command.
     *
     * @param description Input from the user.
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Deletes the specified <code>Task</code> from the <code>TaskList</code>.
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
            Task task = tasks.get(index);
            tasks.delete(index);
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
