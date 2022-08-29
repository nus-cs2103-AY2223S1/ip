package duke.handlers;

import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;

public class UnmarkHandler implements IHandler{
    private String taskIndex;

    public UnmarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    /**
     * Handles the "unmark" command which marks a task as not done.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public void handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `unmark 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            Task item = s.list.get(number - 1);
            item.setDone(false);
            s.ui.customPrint("OK, I've marked this task as not done yet:\n  " + item);
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `unmark 2`");
        }
    }
}