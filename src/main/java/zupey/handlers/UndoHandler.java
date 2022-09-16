package zupey.handlers;

import zupey.exceptions.ZupeyException;
import zupey.service.Service;


/** Handles the user action for marking a Task as done */
public class UndoHandler implements IHandler {

    /**
     * Handles the "mark" command which marks a task as done.
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        try {
            s.undo();
            return "Successfully undid the previous command!";
        } catch (ZupeyException ex) {
            throw ex;
        }
    }
}
