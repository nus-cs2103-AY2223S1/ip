package duke.commands;

import java.io.IOException;

import duke.entities.Task;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Marks the task as done if it is undone otherwise mark it as done
 */
public class Mark extends ShowList {
    protected int indx;

    /**
     * Initialises the Mark command
     * @param list is the task list
     * @param indx is the index of the task to be marked
     * @throws DukeException when the index is invalid
     */
    public Mark(TaskList list, String indx) throws DukeException {
        super(list);
        try {
            this.indx = Integer.parseInt(indx) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }

    /**
     * Unmarks the indx being pointed at
     *
     * @return wrapped message
     * @throws DukeException When the index is out of bound
     * @throws IOException   when there is an IO error
     */
    @Override
    public String execute() throws DukeException, IOException {
        try {
            Task currentTask = tasks.markTask(indx);
            // out.display the marked message
            if (currentTask.isDone()) {
                return wrapWithoutLines(Messages.MARK_DONE.toString(), currentTask.toString());
            } else {
                return wrapWithoutLines(Messages.MARK_UNDONE.toString(), currentTask.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            // Invalid index
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }
}
