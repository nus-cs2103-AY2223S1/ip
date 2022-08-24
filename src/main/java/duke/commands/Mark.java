package duke.commands;

import java.io.IOException;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

public class Mark extends ShowList {
    protected int indx;

    public Mark(TaskList list, String indx) throws DukeException {
        super(list);
        try {
            this.indx = Integer.parseInt(indx) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }

    /**
     * Unmarks the indx being pointed at.
     * 
     * @throws DukeException When the index is out of bound
     * @throws IOException
     */
    @Override
    public void execute() throws DukeException, IOException {
        try {
            Task current_task = tasks.markTask(indx);
            // out.display the marked message
            if (current_task.isDone()) {
                wrapWithLines(Messages.MARK_DONE.toString(), current_task.toString());
            } else {
                wrapWithLines(Messages.MARK_UNDONE.toString(), current_task.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            // Invalid index
            throw new DukeException(Messages.ERROR_INVALID_INDEX.toString());
        }
    }
}
