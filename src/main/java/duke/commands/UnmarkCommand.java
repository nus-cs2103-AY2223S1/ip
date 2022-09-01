package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to mark a <code>Task</code> as not done.
 */
public class UnmarkCommand extends Command {

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
            int index = Integer.parseInt(description.substring(7)) - 1;
            tasks.markTaskAsUndone(index);
            String response = "OK, I've marked this task as not done yet:\n" + tasks.get(index);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("OOPS!!! You cannot mark a non-existent task as undone.");
        }
    }
}
