package duke.handlers;

import duke.entities.Task;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;
import duke.service.Ui;

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
    public void handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
        try {
            Tasklist list = s.getList();
            Ui ui = s.getUi();
            int number = Integer.parseInt(this.taskIndex);
            Task item = list.remove(number - 1);
            ui.customPrint("Noted. I've removed this task:\n"
                    + item
                    + "Now you have " + list.size() + " tasks in the list.");;
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
    }
}
