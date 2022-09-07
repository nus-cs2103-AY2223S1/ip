package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a unmark task command
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates an unmark task command.
     *
     * @param index index of task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task unmarking message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.markAsNotDone(index);
    }
}
