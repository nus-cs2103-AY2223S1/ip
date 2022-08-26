package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to mark a <code>Task</code> as not done.
 */
public class UnMarkCommand extends Command {

    /**
     * Constructs a <code>UnMarkCommand</code> command.
     *
     * @param description Input from the user.
     */
    public UnMarkCommand(String description) {
        super(description);
    }

    /**
     * Marks the user-specified <code>Task</code> as not done.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeIndexOutOfBoundsException If user inputted an index outside the range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(description.substring(7)) - 1;
            tasks.markTaskAsUndone(index);
            ui.printTaskMarkedUndone(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("OOPS!!! You cannot mark a non-existent task as undone.");
        }
    }
}
