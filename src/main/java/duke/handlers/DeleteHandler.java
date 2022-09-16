package duke.handlers;

import duke.entities.Task;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;

/**
 * Handles user action for deleting a Task.
 */
public class DeleteHandler implements IHandler {
    private String taskIndex;

    public DeleteHandler(HandlerFactory factory) {
        this.taskIndex = factory.getTaskName();
    }

    /**
     * Handles the "delete" command by deleting the index input by the user
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public String handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
        try {
            Tasklist list = s.getList();
            int number = Integer.parseInt(this.taskIndex);
            s.saveTasks();
            Task item = list.remove(number - 1);
            int size = list.size();
            return String.format("Noted. I've removed this task:\n"
                    + item
                    + "\n"
                    + "Now you have %d task%s in the list.", size, size != 1 ? "s" : "");
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
    }
}
