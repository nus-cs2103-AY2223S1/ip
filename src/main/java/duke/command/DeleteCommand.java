package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents a command to delete a particular task from the list.
 */
public class DeleteCommand extends Command {
    private final int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) throws DukeException {
        return tasks.deleteTask(pos);
    }
}
