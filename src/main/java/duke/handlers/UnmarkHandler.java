package duke.handlers;

import duke.entities.Task;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.service.Service;
import duke.service.Ui;

/** Handles user action of setting a Task as not done */
public class UnmarkHandler implements IHandler {
    private String taskIndex;

    public UnmarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.getTaskName();
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
        try {
            Tasklist list = s.getList();
            Ui ui = s.getUi();
            int number = Integer.parseInt(this.taskIndex);
            Task item = list.get(number - 1);
            item.setDone(false);
            ui.customPrint("OK, I've marked this task as not done yet:\n  " + item);
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `unmark 2`");
        }
    }
}
