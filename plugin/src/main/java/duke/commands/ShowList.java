package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Displays the entire list of existing tasks
 */
public class ShowList extends DisplayCommand {
    protected TaskList tasks;

    public ShowList(TaskList list) {
        tasks = list;
    }

    /**
     * Prints the current taskings
     *
     * @return wrapped message
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute() throws DukeException, IOException {
        return wrapWithoutLines(tasks.toString());
    }
}
