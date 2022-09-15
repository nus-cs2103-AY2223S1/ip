package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Class to represent the mark command.
 */
public class MarkCommand extends Command {

    /** Index for the task to be marked. */
    private String index;

    /**
     * Constructor for a MarkCommand object.
     * @param index index of task in the list.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * Calls the method to mark the specified task in the list and
     * print the result.
     */
    @Override
    public void executeCommand() throws DukeException {
        TaskList.getInstance().mark(index);
    }
}
