package duke.commands;

import duke.exceptions.DukeException;
import duke.lists.TaskList;

public class ShowList extends Display {
    protected TaskList tasks;

    public ShowList(TaskList list) {
        tasks = list;
    }

    /**
     * Prints the current taskings
     * 
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        wrapWithLines(tasks.toString());
    }
}
