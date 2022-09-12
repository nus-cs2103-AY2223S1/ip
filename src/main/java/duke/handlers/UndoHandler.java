package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;


/** Handles the user action for marking a Task as done */
public class UndoHandler implements IHandler {

    /**
     * Handles the "mark" command which marks a task as done.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public String handle(Service s) throws DukeException {
        try {
            s.undo();
            return "Successfully undid the previous command!";
        } catch (DukeException ex) {
            throw ex;
        }
    }
}
