package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents a command to display the task list sorted according to deadlines.
 */
public class SortCommand extends Command {

    /**
     * {@inheritDoc}
     **/
    public String execute(TaskList tasks) throws DukeException {
        return tasks.sort();
    }
}
