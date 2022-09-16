package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Class for find command.
 */
public class FindCommand extends Command {

    /** String that represents the filter to search by. */
    private String filter;

    /**
     * Constructor for a FindCommand object.
     * @param filter String that represents the filter to search by.
     */
    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Calls the methods to find the relevant tasks with the
     * specified filter and prints it.
     */
    @Override
    public void executeCommand() throws DukeException {
        TaskList.getInstance().findWithFilter(filter);
    }
}
