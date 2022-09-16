package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Command to represent the unmark command.
 */
public class UnmarkCommand extends Command {

    private String index;

    /**
     * Constructor for the UnmarkCommand.
     * @param index index of the item in the list.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Calls the method to unmark the task from the list
     * and print out the result.
     */
    @Override
    public void executeCommand() throws DukeException {
        TaskList.getInstance().unmark(index);
    }
}
