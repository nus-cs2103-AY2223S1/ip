package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to delete a <code>Task</code>.
 */
public class DeleteCommand extends Command {

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
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeIndexOutOfBoundsException If user inputted an index outside the range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(description.substring(7)) - 1;
            Task task = tasks.get(index);
            tasks.delete(index);
            ui.printDeleteTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("OOPS!!! You cannot delete a non-existent task.");
        }
    }
}
