package duke.commands;

import javax.xml.catalog.Catalog;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

public class Delete extends Mark {
    public Delete(TaskList tasks, String indx) throws DukeException {
        super(tasks, indx);
    }

    /**
     * Removes the task at the indx being pointed at.
     * 
     * @throws DukeException When the index is out of bound
     */
    @Override
    public void execute() throws DukeException {
        try {
            Task current_task = tasks.removeTask(indx);
            wrapWithLines(Messages.DELETE.toString(), current_task.toString());

        } catch (IndexOutOfBoundsException e) {
            // Invalid index
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }
}
