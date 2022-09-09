package duke.commands;

import duke.entities.Task;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Deletes a task from the tasklist
 */
public class DeleteCommand extends Mark {
    public DeleteCommand(TaskList tasks, String indx) throws DukeException {
        super(tasks, indx);
    }

    /**
     * Removes the task at the indx being pointed at.
     *
     * @return wrapped message
     * @throws DukeException When the index is out of bound
     */
    @Override
    public String execute() throws DukeException {
        try {
            Task currentTask = tasks.removeTask(indx);
            return wrapWithoutLines(Messages.DELETE.toString(), currentTask.toString());
        } catch (IndexOutOfBoundsException e) {
            // Invalid index
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }
}
