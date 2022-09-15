package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Class representing the delete command.
 */
public class DeleteCommand extends Command {

    /** Index of the task to be deleted. */
    private String index;

    /**
     * Constructor for a DeleteCommand object.
     * @param index index of task to be deleted.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Calls the relevant methods to delete the specified
     * task and print out the deleted task.
     */
    @Override
    public void executeCommand() throws DukeException {
        TaskList.getInstance().delete(index);
    }
}
